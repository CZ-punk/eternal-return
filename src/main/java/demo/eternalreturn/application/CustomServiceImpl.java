package demo.eternalreturn.application;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.domain.model.CharacterStats;
import demo.eternalreturn.domain.model.UserStats;
import demo.eternalreturn.domain.repository.UserStatsRepository;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqUserNicknameDto;
import demo.eternalreturn.infrastructure.proxy.service.EternalReturnService;
import demo.eternalreturn.infrastructure.utils.QueryParamUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.USER_NICKNAME;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.USER_STATS;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomService {

    @Value("${eternal-return.url}")
    private String baseUrl;
    private final int CURRENT_SEASON_ID = 29;

    @Autowired
    private final EternalReturnService eternalReturnService;
    @Autowired
    private final UserStatsRepository userStatsRepository;


    @Override
    @Transactional
    public ResponseEntity<?> searchUserInfoByUsername(ReqUserNicknameDto userNicknameDto) {

        UserStats userStats = userStatsRepository.findByNickname(userNicknameDto.getQuery()).orElse(null);
        if (userStats == null) {
            ReqApiDto request = requestSetting(USER_NICKNAME, userNicknameDto, null, null);
            String endpoint = baseUrl + USER_NICKNAME;

            JsonNode jsonNode = eternalReturnService.callApi(endpoint, request, JsonNode.class).block();
            int userNum = jsonNode.path("user").path("userNum").asInt();
            searchUserStats(userNum, null);
            return ResponseEntity.ok("success");
        }

        Integer userNum = userStats.getUserNum();

        searchUserStats(userNum, userStats);
        // TODO: userNum 을 통해 현시즌(29) userStats 가져와서 저장하고 Json 가공해서 응답하기

        return ResponseEntity.ok("success");

    }

    @Transactional
    protected Object searchUserStats(Integer userNum, UserStats userStats) {

        String pathVariable = "/" + userNum + "/" + CURRENT_SEASON_ID;
        ReqApiDto request = requestSetting(USER_STATS, null, null, pathVariable);
        String endpoint = baseUrl + USER_STATS + pathVariable;

        JsonNode rootNode = eternalReturnService.callApi(endpoint, request, JsonNode.class).block();

        JsonNode data = rootNode.path("userStats").get(0);

        log.info("data: {}", data);

        if (userStats == null) {
            UserStats userStatsEntity = UserStats.builder()
                    .seasonId(data.path("seasonId").asInt())
                    .userNum(data.path("userNum").asInt())
                    .matchingMode(data.path("matchingMode").asInt())
                    .matchingTeamMode(data.path("matchingTeamMode").asInt())
                    .mmr(data.path("mmr").asInt())
                    .nickname(data.path("nickname").asText())
                    .rank(data.path("rank").asInt())
                    .rankSize(data.path("rankSize").asInt())
                    .totalGames(data.path("totalGames").asInt())
                    .totalWins(data.path("totalWins").asInt())
                    .totalTeamKills(data.path("totalTeamKills").asInt())
                    .totalDeaths(data.path("totalDeaths").asInt())
                    .escapeCount(data.path("escapeCount").asInt())
                    .rankPercent(data.path("rankPercent").asDouble())
                    .averageRank(data.path("averageRank").asDouble())
                    .averageKills(data.path("averageKills").asDouble())
                    .averageAssistants(data.path("averageAssistants").asDouble())
                    .averageHunts(data.path("averageHunts").asDouble())
                    .top1(data.path("top1").asDouble())
                    .top2(data.path("top2").asDouble())
                    .top3(data.path("top3").asDouble())
                    .top5(data.path("top5").asDouble())
                    .top7(data.path("top7").asDouble())
                    .build();

            int size = data.path("characterStats").size();
            data.path("characterStats").forEach(character -> {
                userStatsEntity.connectCharacterStats(
                        CharacterStats.builder()
                                .characterCode(character.path("characterCode").asInt())
                                .totalGames(character.path("totalGames").asInt())
                                .usages(character.path("usages").asInt())
                                .maxKillings(character.path("maxKillings").asInt())
                                .top3(character.path("top3").asInt())
                                .wins(character.path("wins").asInt())
                                .top3Rate(character.path("top3Rate").asInt())
                                .averageRank(character.path("averageRank").asInt())
                                .build());
            });

            switch (size) {
                case 1: for (int i=0; i<2; i++) userStatsEntity.connectCharacterStats(CharacterStats.builder().build());
                break;

                case 2: userStatsEntity.connectCharacterStats(CharacterStats.builder().build());
                break;

                default:
            }

            userStatsRepository.save(userStatsEntity);

            return new Object();
        }

        userStats.setSeasonId(data.path("seasonId").asInt());
        userStats.setMatchingMode(data.path("matchingMode").asInt());
        userStats.setMatchingTeamMode(data.path("matchingTeamMode").asInt());
        userStats.setMmr(data.path("mmr").asInt());
        userStats.setNickname(data.path("nickname").asText());
        userStats.setRank(data.path("rank").asInt());
        userStats.setRankSize(data.path("rankSize").asInt());
        userStats.setTotalGames(data.path("totalGames").asInt());
        userStats.setTotalWins(data.path("totalWins").asInt());
        userStats.setTotalTeamKills(data.path("totalTeamKills").asInt());
        userStats.setTotalDeaths(data.path("totalDeaths").asInt());
        userStats.setEscapeCount(data.path("escapeCount").asInt());
        userStats.setRankPercent(data.path("rankPercent").asDouble());
        userStats.setAverageRank(data.path("averageRank").asDouble());
        userStats.setAverageKills(data.path("averageKills").asDouble());
        userStats.setAverageAssistants(data.path("averageAssistants").asDouble());
        userStats.setAverageHunts(data.path("averageHunts").asDouble());
        userStats.setTop1(data.path("top1").asDouble());
        userStats.setTop2(data.path("top2").asDouble());
        userStats.setTop3(data.path("top3").asDouble());
        userStats.setTop5(data.path("top5").asDouble());
        userStats.setTop7(data.path("top7").asDouble());

        JsonNode characterStatsList = data.path("characterStats");

        log.info("characterStatsList: {}", characterStatsList);
        for (int i=0; i<characterStatsList.size(); i++) {

            JsonNode characterStatsNode = characterStatsList.get(i);
            log.info("characterStatsNode: {}", characterStatsNode);

            CharacterStats characterStats = userStats.getCharacterStatsList().get(i);
            log.info("");

            characterStats.update(
                    characterStatsNode.path("characterCode").asInt(),
                    characterStatsNode.path("totalGames").asInt(),
                    characterStatsNode.path("usages").asInt(),
                    characterStatsNode.path("maxKillings").asInt(),
                    characterStatsNode.path("top3").asInt(),
                    characterStatsNode.path("wins").asInt(),
                    characterStatsNode.path("top3Rate").asDouble(),
                    characterStatsNode.path("averageRank").asDouble()
            );
        }


        userStatsRepository.save(userStats);
        return null;
    }


    private ReqApiDto requestSetting(String path, Object paramsDto, Object requestBody, String pathVariable) {
        ReqApiDto request = new ReqApiDto();
        request.setMethod(GET);

        if (paramsDto != null) {
            request.setQueryParams(QueryParamUtils.convertToQueryParams(paramsDto));
        }

        if (requestBody != null) {
            // TODO: body setting
        }

        if (pathVariable != null) {
            request.setPathVariable(pathVariable);
        }

        return request;
    }


}
