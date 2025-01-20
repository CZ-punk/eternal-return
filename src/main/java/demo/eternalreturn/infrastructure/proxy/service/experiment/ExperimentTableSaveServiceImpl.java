package demo.eternalreturn.infrastructure.proxy.service.experiment;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.domain.model.CharacterStats;
import demo.eternalreturn.domain.model.UserStats;
import demo.eternalreturn.domain.model.WeaponTypeInfo;
import demo.eternalreturn.domain.model.constant.WeaponType;
import demo.eternalreturn.domain.model.experiment.*;
import demo.eternalreturn.domain.repository.UserStatsRepository;
import demo.eternalreturn.domain.repository.WeaponTypeInfoRepository;
import demo.eternalreturn.domain.repository.experiment.jpa.*;
import demo.eternalreturn.infrastructure.proxy.dto.response.ResponseDto;
import demo.eternalreturn.infrastructure.proxy.service.util.BulkService;
import demo.eternalreturn.infrastructure.proxy.service.util.JsonNodeService;
import demo.eternalreturn.presentation.exception.ResultMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static demo.eternalreturn.infrastructure.proxy.constant.MetaTypeConst.*;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.DATA;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.USER_STATS;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExperimentTableSaveServiceImpl implements ExperimentTableSaveService {

    private final String DATA_NODE = "data";
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


    @Override
    @Transactional
    public ResponseEntity<?> callWeaponTypeInfo() {
        JsonNode dataNode = jsonNodeService.getJsonNodeByPathVariable(DATA, WEAPON_TYPE_INFO, GET, DATA_NODE);

        List<WeaponTypeInfo> allWeaponTypeInfo = weaponTypeInfoRepository.findAll();
        List<WeaponTypeInfo> weaponTypeInfoList = new ArrayList<>();
        for (JsonNode data : dataNode) {
            WeaponTypeInfo getWeaponTypeInfo = WeaponTypeInfo.builder()
                    .type(WeaponType.valueOf(data.path("type").asText()))
                    .attackSpeed(data.path("attackSpeed").asDouble())
                    .attackRange(data.path("attackRange").asDouble())
                    .shopFilter(data.path("shopFilter").asDouble())
                    .summonObjectHitDamage(data.path("summonObjectHitDamage").asDouble())
                    .build();

            allWeaponTypeInfo.stream().filter(weaponTypeInfo -> weaponTypeInfo.equals(getWeaponTypeInfo))
                    .findAny()
                    .ifPresentOrElse(
                            weaponTypeInfo -> {
                            },
                            () -> weaponTypeInfoList.add(getWeaponTypeInfo));
        }

        bulkService.bulkInsert(weaponTypeInfoList);
        return ResponseEntity.ok("success");
    }

    @Override
    @Transactional
    public ResponseEntity<?> callExperiment() {
        JsonNode dataNode = jsonNodeService.getJsonNodeByPathVariable(DATA, CHARACTER, GET, DATA_NODE);

        List<Experiment> allExperiment = experimentRepository.findAll();
        List<Experiment> experimentList = new ArrayList<>();
        for (JsonNode data : dataNode) {
            Experiment getExperiment = Experiment.builder()
                    .code(data.path("code").asInt())
                    .name(data.path("name").asText())
                    .maxHp(data.path("maxHp").asInt())
                    .maxSp(data.path("maxSp").asInt())
                    .skillAmp(data.path("skillAmp").asInt())
                    .strLearnStartSkill(data.path("strLearnStartSkill").asText())
                    .strUsePointLearnStartSkill(data.path("strUsePointLearnStartSkill").asText())
                    .initExtraPoint(data.path("initExtraPoint").asInt())
                    .maxExtraPoint(data.path("maxExtraPoint").asInt())
                    .attackPower(data.path("attackPower").asInt())
                    .defense(data.path("defense").asInt())
                    .hpRegen(data.path("hpRegen").asDouble())
                    .spRegen(data.path("spRegen").asDouble())
                    .attackSpeed(data.path("attackSpeed").asDouble())
                    .attackSpeedLimit(data.path("attackSpeedLimit").asDouble())
                    .attackSpeedMin(data.path("attackSpeedMin").asDouble())
                    .moveSpeed(data.path("moveSpeed").asDouble())
                    .sightRange(data.path("sightRange").asDouble())
                    .build();

            allExperiment.stream().filter(experiment -> experiment.equals(getExperiment))
                    .findAny()
                    .ifPresentOrElse(
                            experiment -> {
                            }, () -> experimentList.add(getExperiment));
        }

        bulkService.bulkInsert(experimentList);
        return ResponseEntity.ok("success");
    }

    @Override
    @Transactional
    public ResponseEntity<?> callExperimentAttribute() {
        JsonNode dataNode = jsonNodeService.getJsonNodeByPathVariable(DATA, CHARACTER_ATTRIBUTES, GET, DATA_NODE);

        List<ExperimentAttribute> allExperimentAttribute = experimentAttributeRepository.findAll();
        List<ExperimentAttribute> experimentAttributeList = new ArrayList<>();
        for (JsonNode data : dataNode) {
            ExperimentAttribute getExperimentAttribute = ExperimentAttribute.builder()
                    .code(data.path("characterCode").asInt())
                    .name(data.path("character").asText())
                    .mastery(WeaponType.valueOf(data.path("mastery").asText()))
                    .controlDifficulty(data.path("controlDifficulty").asInt())
                    .attack(data.path("attack").asInt())
                    .defense(data.path("defense").asInt())
                    .disruptor(data.path("disruptor").asInt())
                    .move(data.path("move").asInt())
                    .assistance(data.path("assistance").asInt())
                    .build();

            allExperimentAttribute.stream()
                    .filter(experimentAttribute -> experimentAttribute.equals(getExperimentAttribute))
                    .findAny()
                    .ifPresentOrElse(
                            experimentAttribute -> {
                            }, () -> {
                                experimentAttributeList.add(getExperimentAttribute);
                            }
                    );
        }

        bulkService.bulkInsert(experimentAttributeList);
        return ResponseEntity.ok("success");
    }

    @Override
    @Transactional
    public ResponseEntity<?> callExperimentExp() {
        JsonNode dataNode = jsonNodeService.getJsonNodeByPathVariable(DATA, CHARACTER_EXP, GET, DATA_NODE);

        List<ExperimentExp> allExperimentExp = experimentExpRepository.findAll();
        List<ExperimentExp> experimentExpList = new ArrayList<>();
        for (JsonNode data : dataNode) {
            ExperimentExp getExperimentExp = ExperimentExp.builder()
                    .level(data.path("level").asInt())
                    .levelUpExp(data.path("levelUpExp").asInt())
                    .build();

            allExperimentExp.stream().filter(experimentExp -> experimentExp.getLevel().equals(getExperimentExp.getLevel()))
                    .findAny()
                    .ifPresentOrElse(
                            experimentExp -> {
                                if (!experimentExp.getLevelUpExp().equals(getExperimentExp.getLevelUpExp()))
                                    experimentExp.update(getExperimentExp);
                            }, () -> experimentExpList.add(getExperimentExp)
                    );
        }

        bulkService.bulkInsert(experimentExpList);
        return ResponseEntity.ok("success");
    }


    @Override
    @Transactional
    public ResponseEntity<?> callExperimentMastery() {
        JsonNode dataNode = jsonNodeService.getJsonNodeByPathVariable(DATA, CHARACTER_MASTERY, GET, DATA_NODE);

        List<ExperimentMastery> allExperimentMastery = experimentMasteryRepository.findAll();
        List<ExperimentMastery> experimentMasteryList = new ArrayList<>();
        for (JsonNode data : dataNode) {
            ExperimentMastery getExperimentMastery = ExperimentMastery.builder()
                    .code(data.path("code").asInt())
                    .weapon1(data.path("weapon1").asText())
                    .weapon2(data.path("weapon2").asText())
                    .weapon3(data.path("weapon3").asText())
                    .weapon4(data.path("weapon4").asText())
                    .combat1(data.path("combat1").asText())
                    .combat2(data.path("combat2").asText())
                    .survival1(data.path("survival1").asText())
                    .survival2(data.path("survival2").asText())
                    .survival3(data.path("survival3").asText())
                    .build();

            allExperimentMastery.stream().filter(experimentMastery -> experimentMastery.getCode().equals(getExperimentMastery.getCode()))
                    .findAny()
                    .ifPresentOrElse(
                            experimentMastery -> {
                                if (!experimentMastery.equals(getExperimentMastery))
                                    experimentMastery.update(getExperimentMastery);
                            }, () -> experimentMasteryList.add(getExperimentMastery)
                    );
        }

        bulkService.bulkInsert(experimentMasteryList);
        return ResponseEntity.ok("success");
    }

    @Override
    @Transactional
    public ResponseEntity<?> callExperimentLevelUpStat() {
        JsonNode dataNode = jsonNodeService.getJsonNodeByPathVariable(DATA, CHARACTER_LEVEL_UP_STAT, GET, DATA_NODE);

        List<ExperimentLevelUpStat> allExperimentLevelUpStat = experimentLevelUpStatRepository.findAll();
        List<ExperimentLevelUpStat> experimentLevelUpStatList = new ArrayList<>();
        for (JsonNode data : dataNode) {
            ExperimentLevelUpStat getExperimentLevelUpStat = ExperimentLevelUpStat.builder()
                    .code(data.path("code").asInt())
                    .name(data.path("name").asText())
                    .maxHp(data.path("maxHp").asInt())
                    .maxSp(data.path("maxSp").asInt())
                    .attackPower(data.path("attackPower").asDouble())
                    .defense(data.path("defense").asDouble())
                    .criticalChance(data.path("criticalChance").asDouble())
                    .hpRegen(data.path("hpRegen").asDouble())
                    .spRegen(data.path("spRegen").asDouble())
                    .attackSpeed(data.path("attackSpeed").asDouble())
                    .moveSpeed(data.path("moveSpeed").asDouble())
                    .build();

            allExperimentLevelUpStat.stream().filter(experimentLevelUpStat -> experimentLevelUpStat.getCode().equals(getExperimentLevelUpStat.getCode()))
                    .findAny()
                    .ifPresentOrElse(
                            experimentLevelUpStat -> experimentLevelUpStat.update(getExperimentLevelUpStat),
                            () -> experimentLevelUpStatList.add(getExperimentLevelUpStat)
                    );
        }

        bulkService.bulkInsert(experimentLevelUpStatList);
        return ResponseEntity.ok("success");
    }


    @Override
    @Transactional
    public ResponseDto<?> saveUserStats(Integer userNum, UserStats userStats) {
        Integer CURRENT_SEASON_ID = 29;
        String pathVariable = userNum + "/" + CURRENT_SEASON_ID;
        JsonNode data = jsonNodeService.getJsonNodeByPathVariable(USER_STATS, pathVariable, GET, "userStats").get(0);

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

            userStatsRepository.save(userStatsEntity);
            return new ResponseDto<>(HttpStatus.OK, ResultMessage.Success, null);
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
        for (int i = 0; i < characterStatsList.size(); i++) {
            JsonNode characterStatsNode = characterStatsList.get(i);
            CharacterStats characterStats = userStats.getCharacterStatsList().get(i);
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
        return new ResponseDto<>(HttpStatus.OK, ResultMessage.Success, null);
    }

}

