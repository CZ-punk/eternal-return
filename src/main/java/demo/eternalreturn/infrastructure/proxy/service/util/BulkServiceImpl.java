package demo.eternalreturn.infrastructure.proxy.service.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.eternalreturn.presentation.exception.CustomException;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static demo.eternalreturn.infrastructure.proxy.service.util.InstanceUtils.createInstance;

@Slf4j
@Service
@RequiredArgsConstructor
public class BulkServiceImpl implements BulkService {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final ObjectMapper objectMapper;

    // 성능: BulkUpdate > DirtyChecking
    // 단, Json data === Class FieldName
    @Override
    public <T> void comparingAndBulk(Iterator<JsonNode> elements, List<T> all, List<T> insertList, List<T> updateList, Class<T> clazz) {
        while (elements.hasNext()) {
            JsonNode element = elements.next();
            Iterator<String> fieldNames = element.fieldNames();

            T item = createInstance(clazz);
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode value = element.path(fieldName);

                try {
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);

                    if (field.getType() == Integer.class) field.set(item, value.asInt());
                    else if (field.getType() == Double.class) field.set(item, value.asDouble());
                    else if (field.getType() == Boolean.class) field.set(item, value.asBoolean());
                    else if (field.getType() == String.class) field.set(item, value.asText());
                    else if (field.getType().isEnum())
                        field.set(item, Enum.valueOf((Class<? extends Enum>) field.getType(), value.asText()));

//                    else if (List.class.isAssignableFrom(field.getType())) {
//                        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
//                        Class<?> listType = (Class<?>) genericType.getActualTypeArguments()[0];
//                        for (JsonNode node : value) {
//                            Object o = objectMapper.readValue(node.toString(), listType);
//                            item.getClass().getMethod("add" + listType.getSimpleName(), listType).invoke(item, o);
//                        }
//                    }

                } catch (NoSuchFieldException e) {
                    log.debug("해당 필드가 존재하지 않습니다: {}", e.getMessage());
                } catch (IllegalAccessException e) {
                    log.error("해당 필드에 접근할 수 없습니다: {}", e.getMessage());
                } catch (Exception e) {
                    throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }
            }

            boolean exists = all.stream().anyMatch(existingItem -> {
                try {
                    boolean useGeneratedValue = Arrays.stream(clazz.getDeclaredFields()).anyMatch(field -> field.isAnnotationPresent(GeneratedValue.class));
                    return existenceByStrategy(item, existingItem, useGeneratedValue, clazz);

                } catch (Exception e) {
                    log.error("Error occurred while comparing codes: {}", e.getMessage());
                    return false;
                }
            });

            if (exists) updateList.add(item);
            else insertList.add(item);
        }

        bulkInsert(insertList);
        bulkUpdate(updateList);
    }

    private <T> boolean existenceByStrategy(T item, T existingItem, boolean useGeneratedValue, Class<T> clazz) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // generatedValue O || X
        if (useGeneratedValue) {
            Field idField = null;
            for (Field field : clazz.getDeclaredFields())
                if (field.isAnnotationPresent(Id.class)) {
                    idField = field;
                    break;
                }

            if (idField == null)
                throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "not found id field");

            if (existingItem.equals(item)) {
                idField.setAccessible(true);
                Object existingIdValue = idField.get(existingItem);
                idField.set(item, existingIdValue);
                return true;
            }
            return false;

        } else {
            Field idField = null;
            for (Field field : clazz.getDeclaredFields())
                if (field.isAnnotationPresent(Id.class)) {
                    idField = field;
                    break;
                }

            if (idField == null)
                throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "not found id field");

            String getterMethodName = "get" + Character.toUpperCase(idField.getName().charAt(0)) + idField.getName().substring(1);
            return existingItem.getClass().getMethod(getterMethodName).invoke(existingItem).equals(item.getClass().getMethod(getterMethodName).invoke(item));
        }
    }

    private boolean isSupportedType(Class<?> clazz) {
        return clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Double.class) ||
                clazz.equals(Float.class) || clazz.equals(String.class) || clazz.equals(Boolean.class) ||
                clazz.isEnum();
    }

    public <T> void bulkInsert(List<T> objectList) {
        if (objectList.isEmpty()) {
            log.info("bulkInsert: Empty object!");
            return;
        }

        log.info("bulkInsert: start");
        T dto = objectList.getFirst();
        Class<?> aClass = dto.getClass();

        String simpleName = aClass.getSimpleName();
        String tableName = simpleName.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();

        Field[] fields = aClass.getDeclaredFields();
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder placeholders = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(GeneratedValue.class)) continue;

            String columnName = field.isAnnotationPresent(Column.class) ?
                    field.getAnnotation(Column.class).name() :
                    field.getName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();

            sql.append(columnName).append(", ");
            placeholders.append("?, ");
        }

        sql.setLength(sql.length() - 2);
        placeholders.setLength(placeholders.length() - 2);
        sql.append(") VALUES (").append(placeholders).append(")");

        log.info("insert sql: {}", sql.toString());
        jdbcTemplate.batchUpdate(sql.toString(), objectList, objectList.size(),
                (ps, object) -> {
                    List<Field> list = Arrays.stream(fields)
                            .filter(field -> !field.isAnnotationPresent(GeneratedValue.class))
                            .toList();

                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setAccessible(true);
                        try {
                            Object value = list.get(i).get(object);
                            if (!isSupportedType(value.getClass()))
                                throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "bulk insert: not supported type, value: " + value + ", class: " + value.getClass());

                            switch (value) {
                                case Enum e -> ps.setString(i + 1, ((Enum<?>) value).name());
                                case String s -> ps.setString(i + 1, s);
                                case Integer I -> ps.setInt(i + 1, I);
                                case Double d -> ps.setDouble(i + 1, d);
                                case Long l -> ps.setLong(i + 1, l);
                                case Float f -> ps.setFloat(i + 1, f);
                                case null, default -> ps.setObject(i + 1, value);
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Failed to access field: " + fields[i].getName(), e);
                        }
                    }
                });
    }

    public <T> void bulkUpdate(List<T> objectList) {
        if (objectList.isEmpty()) {
            log.info("bulkUpdate Empty object!");
            return;
        }
        log.info("bulkUpdate: start");

        Class<?> aClass = objectList.getFirst().getClass();
        Field[] fields = aClass.getDeclaredFields();
        String tableName = aClass.getSimpleName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();

        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        StringBuilder placeholder = new StringBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Id.class)) {
                placeholder.append(" where ").append(field.getName()).append(" = ?");
                continue;
            }

            String columnName = field.isAnnotationPresent(Column.class) ?
                    field.getAnnotation(Column.class).name() :
                    field.getName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
            sql.append(columnName).append(" = ?, ");
        }
        sql.setLength(sql.length() - 2);
        sql.append(placeholder);

        log.info("update sql: {}", sql.toString());

        jdbcTemplate.batchUpdate(sql.toString(), objectList, objectList.size(), (ps, object) -> {
            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(object);
                    if (!isSupportedType(value.getClass()))
                        throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "bulk update: not supported type, value: " + value + ", class: " + value.getClass());

                    if (field.isAnnotationPresent(Id.class)) {
                        ps.setObject(fields.length, value);
                    } else {
                        switch (value) {
                            case Enum e -> ps.setString(index++, ((Enum<?>) value).name());
                            case String s -> ps.setString(index++, s);
                            case Integer I -> ps.setInt(index++, I);
                            case Double d -> ps.setDouble(index++, d);
                            case Long l -> ps.setLong(index++, l);
                            case Float f -> ps.setFloat(index++, f);
                            default -> ps.setObject(index++, value);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access field: " + field.getName(), e);
                } catch (SQLException e) {
                    log.error("sql exception: {}", e.getMessage());
                    throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }
            }
        });
    }
}
