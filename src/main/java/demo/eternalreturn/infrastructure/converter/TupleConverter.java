package demo.eternalreturn.infrastructure.converter;

import com.querydsl.core.Tuple;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TupleConverter {

    ///  DTO class 의 Type 과 Field Length 고려하여 사용해야 함.
    public static <T> List<T> convertTuplesToDto(List<Tuple> tuples, Class<T> dtoClass) {
        List<T> dtoList = new ArrayList<>();

        try {
            // DTO 클래스의 생성자 가져오기
            Constructor<T> constructor = dtoClass.getDeclaredConstructor(getFieldTypes(dtoClass));
            constructor.setAccessible(true);

            for (Tuple tuple : tuples) {
                // DTO 클래스의 필드 값을 담을 배열
                Object[] fieldValues = new Object[constructor.getParameterCount()];

                // 필드 값을 Tuple에서 가져오기
                for (int i = 0; i < fieldValues.length; i++) {
                    fieldValues[i] = tuple.get(i, constructor.getParameterTypes()[i]);
                }

                log.info("Converting tuple \n{} to \n{}", tuple, fieldValues);

                // DTO 객체 생성
                T dto = constructor.newInstance(fieldValues);
                dtoList.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtoList;
    }

    private static Class<?>[] getFieldTypes(Class<?> dtoClass) {
        Field[] fields = dtoClass.getDeclaredFields();
        Class<?>[] fieldTypes = new Class[fields.length];

        for (int i = 0; i < fields.length; i++) {
            fieldTypes[i] = fields[i].getType();
        }

        return fieldTypes;
    }
}
