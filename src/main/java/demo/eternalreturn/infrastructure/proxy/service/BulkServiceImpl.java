package demo.eternalreturn.infrastructure.proxy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BulkServiceImpl implements BulkService {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Override
    public <T> void bulkInsert(List<T> objectList) {
        if (objectList.isEmpty()) return;

        T dto = objectList.getFirst();
        Class<?> aClass = dto.getClass();

        String simpleName = aClass.getSimpleName();
        String tableName = simpleName.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();

        Field[] fields = aClass.getDeclaredFields();

        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder placeholders = new StringBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(dto);

                if (!(value instanceof Integer || value instanceof Long || value instanceof Double || value instanceof Float || value instanceof String || value instanceof Enum))
                    continue;


                String fieldName = field.getName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
                sql.append(fieldName).append(", ");
                placeholders.append("?, ");

            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access field: " + field.getName(), e);
            }

        }


        sql.setLength(sql.length() - 2);
        placeholders.setLength(placeholders.length() - 2);

        sql.append(") VALUES (").append(placeholders).append(")");
        log.info("sql: {}", sql);


        jdbcTemplate.batchUpdate(sql.toString(), objectList, objectList.size(),
                (ps, object) -> {
                    for (int i = 0; i < fields.length; i++) {
                        fields[i].setAccessible(true);
                        try {
                            Object value = fields[i].get(object);
                            if (!(value instanceof Integer || value instanceof Long || value instanceof Double || value instanceof Float || value instanceof String || value instanceof Enum))
                                continue;

                            log.info("value[{}]: {}", i, value);
                            switch (value) {
                                case Enum e -> ps.setString(i + 1, ((Enum<?>) value).name());
                                case String s -> ps.setString(i + 1, s);
                                case Integer integer -> ps.setInt(i + 1, integer);
                                case Double aDouble -> ps.setDouble(i + 1, aDouble);
                                case Long l -> ps.setLong(i + 1, l);
                                case Float v -> ps.setFloat(i + 1, v);
                                case null, default -> ps.setObject(i + 1, value);
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Failed to access field: " + fields[i].getName(), e);
                        }
                    }
                });
    }


}
