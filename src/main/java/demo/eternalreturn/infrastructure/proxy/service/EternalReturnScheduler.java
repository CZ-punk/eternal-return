//package demo.eternalreturn.infrastructure.proxy.service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import demo.eternalreturn.domain.model.CharacterStats;
//import demo.eternalreturn.domain.model.UserStats;
//import demo.eternalreturn.domain.repository.UserStatsRepository;
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
//    @Autowired
//    private final UserStatsRepository userStatsRepository;
//
//    @Scheduled(fixedRate = 30000) // 1분, 20초
//    @Transactional
//    protected void RANK_TOP_1000_UPDATE() {
//        // TODO: (현재 정규 시즌 6) == (seasonId = 29)
//        // TODO: (현재 팀 모드) == (스쿼드 = 3)
//        String endpoint = baseUrl + RANK_TOP + "/29/3";
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
//
//    @Transactional
//    protected void USER_NUM_SETTING(JsonNode rootNode) {
//        List<UserStats> userStatsList = new ArrayList<>();
//        try {
//            JsonNode dataNode = rootNode.path("topRanks");
//            dataNode.forEach(data -> {
//                UserStats userStats = UserStats.builder().userNum(data.path("userNum").asInt()).build();
//                userStatsList.add(userStats);
//            });
//
//            List<Integer> userNums = userStatsList.stream().map(UserStats::getUserNum).toList();
//
//            List<UserStats> existingUserStats = userStatsList.stream()
//                    .filter(userStats -> userStatsRepository.existsByUserNumIn(userNums))
//                    .toList();
//
//            userStatsRepository.saveAll(existingUserStats);
//        } catch (Exception e) {
//            log.error("서버 오류 발생 USER_NUM_SETTING: " + e.getMessage());
//        } finally {
//            USER_STATS(userStatsList);
//        }
//    }
//
//    @Transactional
//    protected void USER_STATS(List<UserStats> userStatsList) {
//        int batchSize = 10; // 한 번에 보낼 요청 수
//        List<List<UserStats>> partitions = new ArrayList<>();
//        log.info("userStatsList.size(): {}", userStatsList.size());
//
//        for (int i = 0; i < userStatsList.size(); i += batchSize) {
//            partitions.add(userStatsList.subList(i, Math.min(i + batchSize, userStatsList.size())));
//        }
//
//        for (List<UserStats> batch : partitions) {
//            Flux.fromIterable(batch)
//                    .flatMap(userStats -> {
//                        String endpoint = baseUrl + USER_STATS + "/" + userStats.getUserNum() + "/29";
//                        return webClient.get()
//                                .uri(endpoint)
//                                .retrieve()
//                                .onStatus(HttpStatusCode::isError, clientResponse -> {
//                                    if (clientResponse.statusCode() == HttpStatus.TOO_MANY_REQUESTS) {
//                                        log.warn("서버 오류 발생 USER_STATS: " + clientResponse.statusCode() + " - 재시도 가능");
//                                        return Mono.error(new WebClientResponseException("Too Many Requests", HttpStatus.TOO_MANY_REQUESTS.value(), "Too Many Requests", null, null, null));
//                                    } else {
//                                        log.error("서버 오류 발생 USER_STATS: " + clientResponse.statusCode());
//                                        return Mono.error(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred during deployment..."));
//                                    }
//                                })
//                                .bodyToMono(JsonNode.class)
//                                .doOnSuccess(response -> USER_STATS_SETTING(response, batch))
//                                .retryWhen(Retry.backoff(8, Duration.ofSeconds(3)) // 최대 3번 재시도
//                                        .filter(throwable -> throwable instanceof WebClientResponseException &&
//                                                ((WebClientResponseException) throwable).getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) // 429 오류에 대해서만 재시도
//                                        .doBeforeRetry(retrySignal -> {
//                                            log.warn("재시도 중... 시도 횟수: {}", retrySignal.totalRetries());
//                                        }))
//                                .doOnError(e -> log.error("서버에 접속할 수 없습니다: {}", e.getMessage()));
//                    })
//                    .subscribe();
//        }
//    }
//
//    @Transactional
//    protected void USER_STATS_SETTING(JsonNode rootNode, List<UserStats> userStatsList) {
//        try {
//            JsonNode userStatsNode = rootNode.path("userStats");
//            userStatsNode.forEach(userStats -> {
//
//                userStatsList.forEach(entity -> {
//                    if (entity.getUserNum().equals(userStats.path("userNum").asInt())) {
//                        entity.setSeasonId(userStats.path("seasonId").asInt());
//                        entity.setMatchingMode(userStats.path("matchingMode").asInt());
//                        entity.setMatchingTeamMode(userStats.path("matchingTeamMode").asInt());
//                        entity.setMmr(userStats.path("mmr").asInt());
//                        entity.setNickname(userStats.path("nickname").asText());
//
//                        entity.setRank(userStats.path("rank").asInt());
//                        entity.setRankSize(userStats.path("rankSize").asInt());
//                        entity.setTotalGames(userStats.path("totalGames").asInt());
//                        entity.setTotalWins(userStats.path("totalWins").asInt());
//                        entity.setTotalTeamKills(userStats.path("totalTeamKills").asInt());
//                        entity.setTotalDeaths(userStats.path("totalDeaths").asInt());
//                        entity.setEscapeCount(userStats.path("escapeCount").asInt());
//                        entity.setRankPercent(userStats.path("rankPercent").asDouble());
//                        entity.setAverageRank(userStats.path("averageRank").asDouble());
//                        entity.setAverageKills(userStats.path("averageKills").asDouble());
//                        entity.setAverageAssistants(userStats.path("averageAssistants").asDouble());
//                        entity.setAverageHunts(userStats.path("averageHunts").asDouble());
//                        entity.setTop1(userStats.path("top1").asDouble());
//                        entity.setTop2(userStats.path("top2").asDouble());
//                        entity.setTop3(userStats.path("top3").asDouble());
//                        entity.setTop5(userStats.path("top5").asDouble());
//                        entity.setTop7(userStats.path("top7").asDouble());
//
//                        userStats.path("characterStats").forEach(character -> {
//                            entity.connectCharacterStats(CharacterStats.builder()
//                                    .characterCode(character.path("characterCode").asInt())
//                                    .totalGames(character.path("totalGames").asInt())
//                                    .usages(character.path("usages").asInt())
//                                    .maxKillings(character.path("maxKillings").asInt())
//                                    .top3(character.path("top3").asInt())
//                                    .wins(character.path("wins").asInt())
//                                    .top3Rate(character.path("top3Rate").asInt())
//                                    .averageRank(character.path("averageRank").asInt())
//                                    .build());
//                        });
//                    }
//                });
//                userStatsRepository.saveAll(userStatsList);
//            });
//        } catch (Exception e) {
//            log.error("fucking error? what? {}", e.getMessage());
//        }
//    }
//}
