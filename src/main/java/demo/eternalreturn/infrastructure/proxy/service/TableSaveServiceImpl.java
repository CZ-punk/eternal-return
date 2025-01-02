package demo.eternalreturn.infrastructure.proxy.service;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.domain.model.WeaponTypeInfo;
import demo.eternalreturn.domain.model.constant.WeaponType;
import demo.eternalreturn.domain.model.experiment.*;
import demo.eternalreturn.domain.repository.WeaponTypeInfoRepository;
import demo.eternalreturn.domain.repository.experiment.*;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static demo.eternalreturn.infrastructure.proxy.constant.MetaTypeConst.*;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.DATA;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@RequiredArgsConstructor
public class TableSaveServiceImpl implements TableSaveService {

    @Value("${eternal-return.url}")
    private String baseUrl;

    @Autowired
    private final BulkService bulkService;
    @Autowired
    private final EternalReturnService eternalReturnService;
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
    @AutoConfigureOrder
    private final ExperimentLevelUpStatRepository experimentLevelUpStatRepository;


    @Override
    @Transactional
    public ResponseEntity<?> callWeaponTypeInfo() {
        ReqApiDto request = new ReqApiDto();
        request.setPathVariable("/" + WEAPON_TYPE_INFO);
        request.setMethod(GET);
        String endpoint = baseUrl + DATA;
        endpoint += "/" + WEAPON_TYPE_INFO;

        JsonNode dataNode = eternalReturnService.callApi(endpoint, request, JsonNode.class).block().path("data");

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
                            () -> weaponTypeInfoList.add(getWeaponTypeInfo)
                    );
        }
        bulkService.bulkInsert(weaponTypeInfoList);
        return ResponseEntity.ok("success");
    }

    @Override
    @Transactional
    public ResponseEntity<?> callExperiment() {
        ReqApiDto request = new ReqApiDto();
        request.setPathVariable("/" + CHARACTER);
        request.setMethod(GET);
        String endpoint = baseUrl + DATA;
        endpoint += "/" + CHARACTER;

        JsonNode dataNode = eternalReturnService.callApi(endpoint, request, JsonNode.class).block().path("data");

        List<Experiment> allExperiment = experimentRepository.findAll();
        List<Experiment> experimentList = new ArrayList<>();

        for (JsonNode data : dataNode) {
            Experiment getExperiment = Experiment.builder()
                    .code(data.path("code").asInt())
                    .name(data.path("name").asText())
                    .maxHp(data.path("maxHp").asInt())
                    .maxSp(data.path("maxSp").asInt())
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
                            },
                            () -> experimentList.add(getExperiment)
                    );
        }

        bulkService.bulkInsert(experimentList);
        return ResponseEntity.ok("success");
    }

    @Override
    @Transactional
    public ResponseEntity<?> callExperimentAttribute() {
        ReqApiDto request = new ReqApiDto();
        request.setPathVariable("/" + CHARACTER_ATTRIBUTES);
        request.setMethod(GET);
        String endpoint = baseUrl + DATA;
        endpoint += "/" + CHARACTER_ATTRIBUTES;

        JsonNode dataNode = eternalReturnService.callApi(endpoint, request, JsonNode.class).block().path("data");

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

            allExperimentAttribute.stream().filter(
                            experimentAttribute ->
                                    experimentAttribute.getCode().equals(getExperimentAttribute.getCode()) &&
                                            experimentAttribute.getMastery().equals(getExperimentAttribute.getMastery()))
                    .findAny()
                    .ifPresentOrElse(
                            // 있으면, 기존 업데이트
                            experimentAttribute -> experimentAttribute.update(getExperimentAttribute),
                            // 없으면, 새로 저장
                            () -> experimentAttributeList.add(getExperimentAttribute)

                    );
        }
        // TODO: pk 전략 == identity 인 경우 saveAll 을 사용해도 n 개의 query 보냄
        // TODO: 반영구적인 ( 자주변하지 않는 ) 데이터이므로 Trade OFF..
        experimentAttributeRepository.saveAll(experimentAttributeList);
        return ResponseEntity.ok("success");
    }

    @Override
    @Transactional
    public ResponseEntity<?> callExperimentExp() {
        ReqApiDto request = new ReqApiDto();
        request.setPathVariable("/" + CHARACTER_EXP);
        request.setMethod(GET);
        String endpoint = baseUrl + DATA;
        endpoint += "/" + CHARACTER_EXP;

        JsonNode dataNode = eternalReturnService.callApi(endpoint, request, JsonNode.class).block().path("data");
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
                            // 있다면, 조건 확인 후 업데이트
                            experimentExp -> {
                                if (!experimentExp.getLevelUpExp().equals(getExperimentExp.getLevelUpExp()))
                                    experimentExp.update(getExperimentExp);
                            },
                            // 없다면, 새로 저장
                            () -> experimentExpList.add(getExperimentExp)
                    );
        }

        bulkService.bulkInsert(experimentExpList);
        return ResponseEntity.ok("success");
    }


    @Override
    @Transactional
    public ResponseEntity<?> callExperimentMastery() {
        ReqApiDto request = new ReqApiDto();
        request.setPathVariable("/" + CHARACTER_MASTERY);
        request.setMethod(GET);
        String endpoint = baseUrl + DATA;
        endpoint += "/" + CHARACTER_MASTERY;

        JsonNode dataNode = eternalReturnService.callApi(endpoint, request, JsonNode.class).block().path("data");
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
                            },
                            () -> experimentMasteryList.add(getExperimentMastery)
                    );
        }

        bulkService.bulkInsert(experimentMasteryList);
        return ResponseEntity.ok("success");
    }

    @Override
    @Transactional
    public ResponseEntity<?> callExperimentLevelUpStat() {
        ReqApiDto request = new ReqApiDto();
        request.setPathVariable("/" + CHARACTER_LEVEL_UP_STAT);
        request.setMethod(GET);
        String endpoint = baseUrl + DATA;
        endpoint += "/" + CHARACTER_LEVEL_UP_STAT;

        JsonNode dataNode = eternalReturnService.callApi(endpoint, request, JsonNode.class).block().path("data");
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
                            experimentLevelUpStat -> {
                                if (!experimentLevelUpStat.equals(getExperimentLevelUpStat))
                                    experimentLevelUpStat.update(getExperimentLevelUpStat);
                            },
                            () -> experimentLevelUpStatList.add(getExperimentLevelUpStat)
                    );
        }

        bulkService.bulkInsert(experimentLevelUpStatList);
        return ResponseEntity.ok("success");
    }
}

