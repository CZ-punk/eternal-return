package demo.eternalreturn.infrastructure.proxy.service.util;

import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class QueryParamUtils {

    public static Map<String, String> convertToQueryParams(Object dto) {

        Map<String, String> queryParams = new HashMap<>();

        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(dto);
                if (value != null) queryParams.put(field.getName(), value.toString());

            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access field: " + field.getName(), e);
            }
        }

        return queryParams;
    }

}
