//
//
//import com.fasterxml.jackson.databind.JsonNode;
//import demo.eternalreturn.domain.model.eternal_return.user.CharacterStats;
//import demo.eternalreturn.presentation.exception.CustomException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClientResponseException;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.util.retry.Retry;
//
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.RANK_TOP;
//import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.USER_STATS;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class EternalReturnScheduler {
//
//    @Value("${eternal-return.url}")
//    private String baseUrl;
//
//    @Autowired
//    private final WebClient webClient;
//
//
//    @Scheduled(fixedRate = 30000) // 1분, 20초
//    @Transactional
//    protected void RANK_TOP_1000_UPDATE() {
//        // TODO: (현재 정규 시즌 6) == (seasonId = 29)
//        // TODO: (현재 팀 모드) == (스쿼드 = 3)
//        String endpoint = baseUrl + RANK_TOP + "/29/3";
//
//        // TODO: 스케줄러를 통해 항상 업데이트해야 할 목록
//        // 1. TopRank
//        // 2. Experiment 관련 Entity 들
//
//
//        webClient.get()
//                .uri(endpoint)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError,
//                        clientResponse -> {
//                            log.error("서버 오류 발생: " + clientResponse.statusCode());
//                            return Mono.error(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred during deployment..."));
//                        })
//                .bodyToMono(JsonNode.class)
//                .doOnSuccess(this::USER_NUM_SETTING)
//                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))  // 실패 시 2초 간격으로 최대 3번 재시도
//                .doOnError(e -> log.error("서버에 접속할 수 없습니다: {}", e.getMessage()))
//                .subscribe();
//    }