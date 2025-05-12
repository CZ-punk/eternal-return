package demo.eternalreturn.domain.repository.emitter;

import demo.eternalreturn.domain.model.Member.Member;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface EmitterRepository {

    SseEmitter save(String emitterId, SseEmitter sseEmitter);
    void saveEventCache(String emitterId, Object event);

    Map<String, SseEmitter> findAllEmitterStartWithByMemberId(String memberId);
    Map<String, Object> findAllEventCacheStartWithByMemberId(String memberId);

    void deleteById(String emitterId);
    void deleteAllEmitterStartWithMemberId(String memberId);
    void deleteAllEventCacheStartWithMemberId(String memberId);
}
