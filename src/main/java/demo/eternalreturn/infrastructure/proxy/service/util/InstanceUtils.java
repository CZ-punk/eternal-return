package demo.eternalreturn.infrastructure.proxy.service.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@NoArgsConstructor
public class InstanceUtils {

    public static <T> T createInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("인스턴스를 생성할 수 없습니다.", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("인스턴스 접근이 불가능합니다.", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("기본 생성자가 없습니다.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("생성자 호출 중 오류 발생.", e);
        } catch (Exception e) {
            throw new RuntimeException("Fail Create Instance");
        }
    }
}
