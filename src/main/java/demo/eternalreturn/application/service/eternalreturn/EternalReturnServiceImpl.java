package demo.eternalreturn.application.service.eternalreturn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import demo.eternalreturn.domain.model.eternal_return.season.Season;
import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import demo.eternalreturn.domain.repository.eternalreturn.experiment.custom.ExperimentCustomRepository;
import demo.eternalreturn.domain.repository.eternalreturn.player.custom.TopRankCustomRepository;
import demo.eternalreturn.domain.repository.eternalreturn.player.jpa.UserStatsRepository;
import demo.eternalreturn.domain.repository.eternalreturn.season.SeasonRepository;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import demo.eternalreturn.infrastructure.proxy.dto.response.ResUserNicknameDto;
import demo.eternalreturn.infrastructure.proxy.service.ProxyService;
import demo.eternalreturn.infrastructure.proxy.service.experiment.ExperimentTableSaveService;
import demo.eternalreturn.infrastructure.proxy.service.util.JsonNodeService;
import demo.eternalreturn.infrastructure.proxy.service.util.QueryParamUtils;
import demo.eternalreturn.presentation.dto.request.eternal_return.Req20GamesDto;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqExperimentCodeDto;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqUserNicknameDto;
import demo.eternalreturn.presentation.dto.response.eternalreturn.experiment.ResExperimentStatDto;
import demo.eternalreturn.presentation.dto.response.eternalreturn.season.ResSeasonDto;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.*;

import static demo.eternalreturn.infrastructure.proxy.constant.DataNodeConst.*;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.USER_GAMES;
import static demo.eternalreturn.presentation.dto.response.eternalreturn.user.ResTopRankDto.UserRankDto;
import static org.springframework.http.HttpMethod.GET;


@Slf4j
@Service
@RequiredArgsConstructor
public class EternalReturnServiceImpl implements EternalReturnService {

    @Value("${eternal-return.url}")
    private String baseUrl;
    @Autowired
    private final ExperimentTableSaveService tableSaveService;
    @Autowired
    private final UserStatsRepository userStatsRepository;
    @Autowired
    private final ProxyService proxyService;
    @Autowired
    private final ExperimentCustomRepository experimentCustomRepository;
    @Autowired
    private final TopRankCustomRepository topRankCustomRepository;
    @Autowired
    private final SeasonRepository seasonRepository;
    @Autowired
    private final JsonNodeService jsonNodeService;


    /**
     * Username 을 기반으로 UserStats 정보를 새로 생성 및 업데이트하는 함수
     */
    @Override
    @Transactional
    public ResUserNicknameDto searchUserInfoByUsername(ReqUserNicknameDto userNicknameDto) {
        UserStats userStats = userStatsRepository.findByNickname(userNicknameDto.getQuery()).orElse(null);
        if (userStats == null) userStats = tableSaveService.registerUserStats(userNicknameDto);
        else userStats = tableSaveService.updateUserStats(userStats);


        // TODO: userNum 추출 후 => 프론트에서 추가 api 인 game Infos 호출하여 2번 처리

        return new ResUserNicknameDto(userStats.getUserNum(), userStats.getNickname());
    }

    /**
     * ExperimentCode 를 기반으로 Experiment Stats 및 Level Up Stat 을 조회
     */
    @Override
    @Transactional(readOnly = true)
    public ResExperimentStatDto searchExperimentStatByExperimentCode(ReqExperimentCodeDto experimentCodeDto) {
        return experimentCustomRepository.searchExperimentStatByExperimentCode(experimentCodeDto.getCode());
    }

    /**
     * Top Rank 를 Rank 순으로 Pagination 처리
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserRankDto> searchTopRankPage(Pageable pageable) {
        return topRankCustomRepository.searchTopRankPage(pageable);
    }

    /**
     * Season Info 조회
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResSeasonDto> getSeasonList() {
        return seasonRepository.findAll().stream()
                .sorted(Comparator.comparing(Season::getSeasonID).reversed())
                .map(ResSeasonDto::new)
                .toList();
    }

    /**
     * EternalReturn 서버로부터 20개의 Game info 요청
     * 일반, 랭크 상관 X
     */

    @Override
    @Transactional
    public Mono<?> getGames(Req20GamesDto req20GamesDto) {


        // TODO: game repository 조회 method
        // TODO: 없을 경우 아래 로직

        List<JsonNode> combinedGamesNodes = new ArrayList<>();
        /**
         * 멈추는 조건
         * 1. json data 의 seasonId 가 dto 의 seasonId 와 일치하지 않는 경우
         * 2. json data 의 next 필드가 null 인 경우
         * 3. 추출한 data 의 양이 20개인 경우
         * 
         * 단, 한번 조회한 데이터는 db 에 저장
         */

        ReqApiDto request = createReqApiDto(req20GamesDto.getUserNum().toString(), null, null);
        String endpoint = baseUrl + USER_GAMES + request.getPathVariable();


        return fetchGames(endpoint, request, combinedGamesNodes, req20GamesDto.getSeasonId())
                .flatMap(result -> {

                    JsonNode finalJson = jsonNodeService.createFinalJson(USER_GAMES_NODE, combinedGamesNodes);
                    if (finalJson instanceof ObjectNode) ((ObjectNode) finalJson).put(NEXT_NODE, result);
                    return Mono.just(finalJson);
                })
                .doOnError(error -> {
                    log.error(error.toString());
                    throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, error.toString());
                });
    }

    // TODO: 지금하는게 랭크에 대한 20개의 데이터 가져오기
    @Transactional
    protected Mono<String> fetchGames(String endpoint, ReqApiDto request, List<JsonNode> combinedGamesNodes, String seasonId) {
        return proxyService.callApi(endpoint, request, JsonNode.class)
                .flatMap(jsonNode -> {
                    JsonNode userGamesNode = jsonNode.path(USER_GAMES_NODE);
                    String next = jsonNode.path(NEXT_NODE).asText();
                    boolean flag = false;

                    if (userGamesNode == null)
                        return Mono.error(new CustomException(HttpStatus.NOT_FOUND, "not found player info by user num"));

                    Iterator<JsonNode> elements = userGamesNode.elements();
                    while (elements.hasNext()) {
                        JsonNode element = elements.next();
                        String season = element.path(SEASON_ID_NODE).asText();
                        if (season.equals("0")) continue;
                        if (season != null && season.equals(seasonId)) combinedGamesNodes.add(element);
                        else if (Integer.valueOf(seasonId) > Integer.valueOf(season)) flag = true;
                    }

                    if (flag) return Mono.just(next);

                    log.info(combinedGamesNodes.size() + " games found");
                    if (next != null && combinedGamesNodes.size() < 20) {
                        request.setQueryParams(Map.of(NEXT_NODE, next));
                        return fetchGames(endpoint, request, combinedGamesNodes, seasonId);
                    }

                    return Mono.just(next);
                });
    }


    //                    jsonNode.path(USER_GAMES_NODE).forEach(userGamesNode -> {
//                        if (userGamesNode.path(MATCHING_MODE_NODE).asInt() == 3)
//                            combinedRankNodes.add(userGamesNode);
//                    });

    private ReqApiDto createReqApiDto(String pathVariable, Object queryParams, Object requestBody) {
        ReqApiDto request = new ReqApiDto();
        request.setMethod(GET);

        if (queryParams != null) request.setQueryParams(QueryParamUtils.convertToQueryParams(queryParams));
        if (requestBody != null) request.setRequestBody(requestBody);
        if (pathVariable != null) request.setPathVariable(pathVariable);

        return request;
    }


}
