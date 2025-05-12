package demo.eternalreturn.application.service.notification;

import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.model.notification.Notification;
import demo.eternalreturn.domain.model.notification.NotificationType;
import demo.eternalreturn.domain.repository.emitter.EmitterRepository;
import demo.eternalreturn.domain.repository.member.MemberRepository;
import demo.eternalreturn.domain.repository.notification.NotificationRepository;
import demo.eternalreturn.presentation.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

import static demo.eternalreturn.domain.model.notification.Notification.create;
import static org.springframework.retry.policy.TimeoutRetryPolicy.DEFAULT_TIMEOUT;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final EmitterRepository emitterRepository;
    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;

    @Override
    public SseEmitter subscribe(Long memberId, String lastEventId) {

        String emitterId = memberId + "_" + System.currentTimeMillis();
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT * 60));

        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));



        /// connection 이후 데이터 전송없이 유효시간 끝나면 503 Error
        /// Error 방지를 위한 Dummy Data 전송

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "not found member"));
        send(member, NotificationType.INIT, "content", "url");
        sendToClient(emitter, emitterId, "EventStream Created. [ memberId: " + memberId + " ]");
        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = emitterRepository.findAllEventCacheStartWithByMemberId(memberId.toString());
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
    }

    private void send(Member member, NotificationType notificationType, String content, String url) {
        Notification notification = notificationRepository.save(create(member, notificationType, content, url));
        String memberId = member.getId().toString();

        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterStartWithByMemberId(memberId);
        sseEmitters.forEach(
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, notification);
                    sendToClient(emitter, key, notification.getUrl());
                }
        );
    }

    private void sendToClient(SseEmitter emitter, String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(emitterId)
                    .data(data));

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "SSE FAILURE SEND TO CLIENT");
        }
    }

    @Transactional
    public void approveCollaboRequest(Long collaboRequestId, Member member) {

        ///  요청한 Member 한테 승인 완료 알림 - 게시글 상세 조회로 이동

    }


}
