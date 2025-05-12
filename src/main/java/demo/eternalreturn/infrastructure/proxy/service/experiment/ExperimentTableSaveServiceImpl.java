package demo.eternalreturn.infrastructure.proxy.service.experiment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.eternalreturn.domain.model.eternal_return.experiment.*;
import demo.eternalreturn.domain.model.eternal_return.item.WeaponTypeInfo;
import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import demo.eternalreturn.domain.repository.eternalreturn.experiment.jpa.*;
import demo.eternalreturn.domain.repository.eternalreturn.item.jpa.WeaponTypeInfoRepository;

import demo.eternalreturn.domain.repository.eternalreturn.player.jpa.UserStatsRepository;
import demo.eternalreturn.infrastructure.proxy.dto.response.ResUserNicknameDto;
import demo.eternalreturn.infrastructure.proxy.service.util.BulkService;
import demo.eternalreturn.infrastructure.proxy.service.util.JsonNodeService;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqUserNicknameDto;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static demo.eternalreturn.infrastructure.proxy.constant.MetaTypeConst.*;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExperimentTableSaveServiceImpl implements ExperimentTableSaveService {

    private final String DATA_NODE = "data";
    private final String USER_STATS_NODE = "userStats";
    private final String USER_NODE = "user";
    private final Integer CURRENT_SEASON_ID = 31;
    @Autowired
    private final BulkService bulkService;
    @Autowired
    private final JsonNodeService jsonNodeService;
    @Autowired
    private final WeaponTypeInfoRepository weaponTypeInfoRepository;
    @Autowired
    private final ExperimentRepository experimentRepository;
    @Autowired
    private final ExperimentAttributeRepository experimentAttributeRepository;
    @Autowired
    private final ExperimentExpRepository experimentExpRepository;
    @Autowired
    private final ExperimentMasteryRepository experimentMasteryRepository;
    @Autowired
    private final ExperimentLevelUpStatRepository experimentLevelUpStatRepository;
    @Autowired
    private final UserStatsRepository userStatsRepository;
//    @Autowired
//    private final CharacterStatsRepository characterStatsRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    @Transactional
    public Mono<?> callWeaponTypeInfo() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, WEAPON_TYPE_INFO, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DATA_NODE);

                    List<WeaponTypeInfo> all = weaponTypeInfoRepository.findAll();
                    List<WeaponTypeInfo> insertList = new ArrayList<>();
                    List<WeaponTypeInfo> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, WeaponTypeInfo.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callExperiment() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, CHARACTER, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DATA_NODE);

                    List<Experiment> all = experimentRepository.findAll();
                    List<Experiment> insertList = new ArrayList<>();
                    List<Experiment> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, Experiment.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callExperimentAttribute() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, CHARACTER_ATTRIBUTES, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DATA_NODE);

                    List<ExperimentAttribute> all = experimentAttributeRepository.findAll();
                    List<ExperimentAttribute> insertList = new ArrayList<>();
                    List<ExperimentAttribute> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ExperimentAttribute.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callExperimentExp() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, CHARACTER_EXP, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DATA_NODE);

                    List<ExperimentExp> all = experimentExpRepository.findAll();
                    List<ExperimentExp> insertList = new ArrayList<>();
                    List<ExperimentExp> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ExperimentExp.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callExperimentMastery() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, CHARACTER_MASTERY, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DATA_NODE);

                    List<ExperimentMastery> all = experimentMasteryRepository.findAll();
                    List<ExperimentMastery> insertList = new ArrayList<>();
                    List<ExperimentMastery> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ExperimentMastery.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });

    }

    @Override
    @Transactional
    public Mono<?> callExperimentLevelUpStat() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, CHARACTER_LEVEL_UP_STAT, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DATA_NODE);

                    List<ExperimentLevelUpStat> all = experimentLevelUpStatRepository.findAll();
                    List<ExperimentLevelUpStat> insertList = new ArrayList<>();
                    List<ExperimentLevelUpStat> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ExperimentLevelUpStat.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public UserStats registerUserStats(ReqUserNicknameDto reqUserNicknameDto) {
        return jsonNodeService.getMonoJsonNodeByQueryParams(USER_NICKNAME, reqUserNicknameDto, GET)
                .flatMap(jsonNode -> {
                    JsonNode userNode = jsonNode.path(USER_NODE);
                    ResUserNicknameDto resUserNicknameDto = jsonNodeService.extractDto(userNode, ResUserNicknameDto.class);

                    return Mono.just(resUserNicknameDto);
                })
                .doOnError(e -> log.error("Error During check userNum: ", e))
                .flatMap(dto -> {
                    if (dto == null) return Mono.error(new CustomException(HttpStatus.NO_CONTENT, "No content found"));

                    Integer userNum = dto.getUserNum();
                    String pathVariable = userNum + "/" + CURRENT_SEASON_ID;
                    return jsonNodeService.getMonoJsonNodeByPathVariable(USER_STATS, pathVariable, GET)
                            .flatMap(jsonNode -> {
                                JsonNode userStatsNode = jsonNode.path(USER_STATS_NODE);
                                Iterator<JsonNode> elements = userStatsNode.elements();
                                UserStats result = jsonNodeService.jsonMapping(elements, UserStats.class);
                                if (result == null)
                                    return Mono.error(new CustomException(INTERNAL_SERVER_ERROR, "No result found"));
                                return Mono.just(userStatsRepository.save(result));
                            });
                }).block();
    }

    @Override
    @Transactional
    public UserStats updateUserStats(UserStats userStats) {
        Integer userNum = userStats.getUserNum();
        String pathVariable = userNum + "/" + CURRENT_SEASON_ID;

        return jsonNodeService.getMonoJsonNodeByPathVariable(USER_STATS, pathVariable, GET)
                .flatMap(jsonNode -> {
                    JsonNode userStatsNode = jsonNode.path(USER_STATS_NODE);
                    Iterator<JsonNode> elements = userStatsNode.elements();
                    UserStats result = jsonNodeService.jsonMapping(elements, UserStats.class);

                    if (result == null)
                        return Mono.error(new CustomException(INTERNAL_SERVER_ERROR, "not found data: " + userStats.getNickname()));

                    userStatsRepository.save(userStats.update(result));
                    return Mono.just(userStats);
                }).block();
    }

}

