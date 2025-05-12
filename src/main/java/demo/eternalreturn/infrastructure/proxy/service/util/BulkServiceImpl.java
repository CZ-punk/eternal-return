package demo.eternalreturn.infrastructure.proxy.service.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.eternalreturn.domain.model.eternal_return.user.CharacterStats;
import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import demo.eternalreturn.domain.repository.eternalreturn.player.jpa.CharacterStatsRepository;
import demo.eternalreturn.domain.repository.eternalreturn.player.jpa.UserStatsRepository;
import demo.eternalreturn.presentation.exception.CustomException;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static demo.eternalreturn.infrastructure.proxy.service.util.InstanceUtils.createInstance;

@Slf4j
@Service
@RequiredArgsConstructor
public class BulkServiceImpl implements BulkService {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private final ObjectMapper objectMapper;
    @Autowired
    private UserStatsRepository userStatsRepository;
    @Autowired
    private CharacterStatsRepository characterStatsRepository;


    // 성능: BulkUpdate > DirtyChecking
    // 단, Json data === Class FieldName
    @Override
    @Transactional
    public <T> void comparingAndBulk(Iterator<JsonNode> elements, List<T> all, List<T> insertList, List<T> updateList, Class<T> clazz) {
        while (elements.hasNext()) {

            T item = createInstanceByObject(elements.next(), clazz);

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

    @Override
    @Transactional
    public <T> T createInstanceByObject(JsonNode dataNode, Class<T> clazz) {
        T item = createInstance(clazz);
        Iterator<String> fieldNames = dataNode.fieldNames();

        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode value = dataNode.path(fieldName);

            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);

                if (field.getType() == Integer.class) field.set(item, value.asInt());
                else if (field.getType() == Double.class) field.set(item, value.asDouble());
                else if (field.getType() == Boolean.class) field.set(item, value.asBoolean());
                else if (field.getType() == String.class) field.set(item, value.asText());
                else if (field.getType().isEnum())
                    field.set(item, Enum.valueOf((Class<? extends Enum>) field.getType(), value.asText()));

            } catch (NoSuchFieldException e) {
                log.debug("해당 필드가 존재하지 않습니다: {}", e.getMessage());
            } catch (IllegalAccessException e) {
                log.error("해당 필드에 접근할 수 없습니다: {}", e.getMessage());
            } catch (Exception e) {
                throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }

        }

        return item;
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
        try {
            return clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Double.class) ||
                    clazz.equals(Float.class) || clazz.equals(String.class) || clazz.equals(Boolean.class) ||
                    clazz.isEnum() || clazz.equals(LocalDateTime.class) || clazz.equals(UserStats.class);
        } catch (NullPointerException e) {
            log.error("null point error: {}", e.getMessage());
            return false;
        }
    }

    public <T> void bulkInsert(List<T> objectList) {
        if (objectList.isEmpty()) {
            log.info("bulkInsert: Empty object!");
            return;
        }

        log.info("bulkInsert: start: {}", objectList.size());
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
            if (List.class.isAssignableFrom(field.getType())) continue;

            String columnName = getColumnName(field);
            sql.append(columnName).append(", ");
            placeholders.append("?, ");
        }

        sql.setLength(sql.length() - 2);
        placeholders.setLength(placeholders.length() - 2);
        sql.append(") VALUES (").append(placeholders).append(")");

        log.info("bulkInsert: {}", sql.toString());
        jdbcTemplate.batchUpdate(sql.toString(), objectList, objectList.size(),
                (ps, object) -> {
                    List<Field> list = Arrays.stream(fields)
                            .filter(field -> !field.isAnnotationPresent(GeneratedValue.class))
                            .toList();

                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setAccessible(true);
                        try {
                            Object value = list.get(i).get(object);

                            if (value instanceof UserStats userStats) {
                                log.info("value is UserStats Type!: {}", value);
                                value = userStats.getUserNum();
                            }

                            if (!isSupportedType(value.getClass()))
                                throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "bulk insert: not supported type, value: " + value + ", class: " + value.getClass());

                            switch (value) {
                                case Enum e -> ps.setString(i + 1, ((Enum<?>) value).name());
                                case String s -> ps.setString(i + 1, s);
                                case Integer I -> ps.setInt(i + 1, I);
                                case Double d -> ps.setDouble(i + 1, d);
                                case Long l -> ps.setLong(i + 1, l);
                                case Float f -> ps.setFloat(i + 1, f);
                                case LocalDateTime ldt -> ps.setTimestamp(i + 1, Timestamp.valueOf(ldt));
                                case null, default -> ps.setObject(i + 1, null);
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Failed to access field: " + fields[i].getName(), e);
                        } catch (Exception e) {
                            log.error("insert sql failed: {}", sql, e);
                        }
                    }
                });
    }

    public <T> void bulkUpdate(List<T> objectList) {
        if (objectList.isEmpty()) {
            log.info("bulkUpdate Empty object!");
            return;
        }
        log.info("bulkUpdate: start: {}", objectList.size());

        Class<?> aClass = objectList.getFirst().getClass();
        Field[] fields = aClass.getDeclaredFields();
        String tableName = aClass.getSimpleName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();

        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        StringBuilder placeholder = new StringBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            if (List.class.isAssignableFrom(field.getType())) continue;
            if (field.isAnnotationPresent(Id.class)) {
                placeholder.append(" where ");
                placeholder.append(getColumnName(field)).append(" = ?");
                continue;
            }
            sql.append(getColumnName(field)).append(" = ?, ");
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
                            case LocalDateTime ldt -> ps.setTimestamp(index++, Timestamp.valueOf(ldt));
                            default -> ps.setObject(index++, null);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access field: " + field.getName(), e);
                } catch (SQLException e) {
                    log.error("sql exception: {}", e.getMessage());
                    throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                } catch (Exception e) {
                    log.error("update sql failed: {}", sql, e);
                }
            }
        });
    }

    private static String getColumnName(Field field) {
        String columnName = field.isAnnotationPresent(Column.class) ?
                field.getAnnotation(Column.class).name() :
                field.getName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
        return columnName;
    }


    @Override
    @Transactional
    public <P, C> void relationShipBulk(Iterator<JsonNode> elements, Class<P> parent, Class<C> child) {
        List<P> parentList = new ArrayList<>();
        List<C> childList = new ArrayList<>();

        while (elements.hasNext()) {
            JsonNode element = elements.next();
            try {
                P item = objectMapper.readValue(element.toString(), parent);
                parentList.add(item);
                createSubObject(item, element.path("characterStats"), child, childList);

            } catch (IOException e) {
                log.error("JSON 변환 중 오류 발생: {}", e.getMessage());
                throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }

        List<UserStats> allUserStats = userStatsRepository.findAll(
                parentList.stream().map(this::extractPrimaryKey).toList()
        );

        List<CharacterStats> allCharacterStats = characterStatsRepository.findAllByUserNum(
                parentList.stream().map(this::extractPrimaryKey).toList()
        );

        List<P> insertList = new ArrayList<>();
        List<P> updateList = new ArrayList<>();
        List<C> insertChildList = new ArrayList<>();
        List<C> updateChildList = new ArrayList<>();

        for (P entity : parentList) {
            Integer pk = extractPrimaryKey(entity);
            Optional<UserStats> optionalUserStats = allUserStats.stream()
                    .filter(userStats -> userStats.getUserNum().equals(pk))
                    .findAny();

            if (optionalUserStats.isPresent()) updateList.add(entity);
            else insertList.add(entity);
        }

        for (C entity : childList) {
            Optional<CharacterStats> optionalCharacterStats = allCharacterStats.stream()
                    .filter(characterStats -> characterStats.equals(entity))
                    .findFirst();

            if (optionalCharacterStats.isPresent()) updateChildList.add((C) optionalCharacterStats.get());
            else insertChildList.add(entity);
        }

        log.info("insertChild.size(): {}", insertChildList.size());
        log.info("updateChild.size(): {}", updateChildList.size());

        bulkInsert(insertList);
        bulkUpdate(updateList);
        bulkInsert(insertChildList);
        bulkUpdate(updateChildList);


    }

    private <P, C> void createSubObject(P item, JsonNode element, Class<C> child, List<C> childList) {
        try {
            Integer itemId = extractPrimaryKey(item);

            C childObject = null;
            Iterator<JsonNode> elements = element.elements();

            while (elements.hasNext()) {
                JsonNode node = elements.next();
                childObject = objectMapper.readValue(node.toString(), child);
                Field userNumField = childObject.getClass().getDeclaredField("userNum");
                userNumField.setAccessible(true);
                userNumField.set(childObject, itemId);

                childList.add(childObject);
            }
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "setRelationShips Error");
        }
    }

    private <T> Integer extractPrimaryKey(T item) {
        try {
            Field[] declaredFields = item.getClass().getDeclaredFields();
            Integer itemId = null;
            for (Field field : declaredFields)
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    itemId = (Integer) field.get(item);
                    break;
                }
            return itemId;

        } catch (IllegalAccessException e) {
            log.error("not found item pk");
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
