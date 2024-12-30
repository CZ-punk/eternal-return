package demo.eternalreturn.infrastructure.proxy.service;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.domain.model.WeaponTypeInfo;
import demo.eternalreturn.domain.model.constant.WeaponType;
import demo.eternalreturn.domain.model.experiment.Experiment;
import demo.eternalreturn.domain.model.experiment.ExperimentAttribute;
import demo.eternalreturn.domain.repository.WeaponTypeInfoRepository;
import demo.eternalreturn.domain.repository.experiment.ExperimentAttributeRepository;
import demo.eternalreturn.domain.repository.experiment.ExperimentRepository;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
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

        List<Experiment> allExperiment = experimentRepository.findAll();
        List<ExperimentAttribute> allExperimentAttribute = experimentAttributeRepository.findAll();
        List<ExperimentAttribute> experimentAttributeList = new ArrayList<>();

        for (JsonNode data : dataNode) {

            ExperimentAttribute getExperimentAttribute = ExperimentAttribute.builder()
                    .code(data.path("characterCode").asInt())
                    .mastery(WeaponType.valueOf(data.path("mastery").asText()))
                    .controlDifficulty(data.path("controlDifficulty").asInt())
                    .attack(data.path("attack").asInt())
                    .defense(data.path("defense").asInt())
                    .disruptor(data.path("disruptor").asInt())
                    .move(data.path("move").asInt())
                    .assistance(data.path("assistance").asInt())
                    .build();

            allExperiment.stream().filter(experiment -> experiment.getCode().equals(getExperimentAttribute.getCode()))
                    .findAny()
                    .ifPresentOrElse(
                            experiment -> {
                                experiment.connectExperimentAttribute(getExperimentAttribute);
                            },
                            () -> {
                                throw new CustomException(HttpStatus.CONFLICT, "no experiment to connect");
                            }
                    );

            allExperimentAttribute.stream().filter(experimentAttribute -> experimentAttribute.equals(getExperimentAttribute))
                    .findAny()
                    .ifPresentOrElse(
                            experimentAttribute -> {
                            },
                            () -> experimentAttributeList.add(getExperimentAttribute)
                    );
        }

        experimentAttributeRepository.saveAll(experimentAttributeList);
        return ResponseEntity.ok("success");
    }


}
