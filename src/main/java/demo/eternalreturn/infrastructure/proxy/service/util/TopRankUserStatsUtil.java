package demo.eternalreturn.infrastructure.proxy.service.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.eternalreturn.domain.model.eternal_return.user.CharacterStats;
import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.USER_STATS;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Component
@RequiredArgsConstructor
public class TopRankUserStatsUtil {

    private final Integer CURRENT_SEASON_ID = 31;
    private final String USER_STATS_NODE = "userStats";
    private final ObjectMapper objectMapper;

    private final JsonNodeService jsonNodeService;
    private final BulkService bulkService;
    private List<JsonNode> combinedUserStats = new ArrayList<>();


    @Value("${eternal-return.url}")
    private String baseUrl;

    public void fetchUserStats(List<Integer> userNumList) {
        Map<ReqApiDto, String> requestMap = new HashMap<>();
        for (Integer userNum : userNumList) {

            ReqApiDto request = new ReqApiDto();
            String pathVariable = userNum + "/" + CURRENT_SEASON_ID;
            request.setPathVariable("/" + userNum + "/" + CURRENT_SEASON_ID);
            request.setMethod(GET);
            String endpoint = baseUrl + USER_STATS;
            endpoint += "/" + pathVariable;

            requestMap.put(request, endpoint);
        }

        jsonNodeService.getMonoJsonNodeByPathVariableRateLimit(requestMap, GET)
                .subscribe(result -> {
                    result.forEach(jsonNode -> {
                        JsonNode userStatsNode = jsonNode.path(USER_STATS_NODE);
                        if (userStatsNode.isArray()) userStatsNode.forEach(combinedUserStats::add);
                    });

                    JsonNode finalJson = jsonNodeService.createFinalJson(USER_STATS_NODE, combinedUserStats);
                    bulkService.relationShipBulk(finalJson.path(USER_STATS_NODE).elements(), UserStats.class, CharacterStats.class);

                }, error -> {
                    System.out.println("error = " + error);
                    log.error(error.getMessage());
                });

        combinedUserStats.clear();
    }
}
