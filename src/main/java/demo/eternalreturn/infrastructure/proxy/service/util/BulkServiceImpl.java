package demo.eternalreturn.infrastructure.proxy.service.util;

import demo.eternalreturn.presentation.exception.CustomException;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BulkServiceImpl implements BulkService {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Override
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

            String fieldName = field.getName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
            sql.append(fieldName).append(", ");
            placeholders.append("?, ");
        }

        sql.setLength(sql.length() - 2);
        placeholders.setLength(placeholders.length() - 2);
        sql.append(") VALUES (").append(placeholders).append(")");

        jdbcTemplate.batchUpdate(sql.toString(), objectList, objectList.size(),
                (ps, object) -> {
                    List<Field> list = Arrays.stream(fields)
                            .filter(field -> !"id".equals(field.getName()))
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

    @Override
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
            String fieldName = field.getName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
            sql.append(fieldName).append(" = ?, ");
        }
        sql.setLength(sql.length() - 2);
        sql.append(placeholder);

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

    private boolean isSupportedType(Class<?> clazz) {
        return clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Double.class) ||
                clazz.equals(Float.class) || clazz.equals(String.class) || clazz.equals(Boolean.class) ||
                clazz.isEnum();
    }
}
