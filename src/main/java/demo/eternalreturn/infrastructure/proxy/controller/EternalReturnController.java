package demo.eternalreturn.infrastructure.proxy.controller;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import demo.eternalreturn.presentation.controller.dto.request.ReqUserNicknameDto;
import demo.eternalreturn.infrastructure.proxy.service.EternalReturnService;
import demo.eternalreturn.infrastructure.proxy.service.experiment.ExperimentTableSaveService;
import demo.eternalreturn.infrastructure.proxy.service.item.ItemTableSaveService;
import demo.eternalreturn.infrastructure.proxy.service.util.QueryParamUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.DATA;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.USER_NICKNAME;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/eternal-return")
public class EternalReturnController {

    @Value("${eternal-return.url}")
    private String baseUrl;

    @Autowired
    private EternalReturnService eternalReturnService;
    @Autowired
    private ExperimentTableSaveService experimentTableSaveService;
    @Autowired
    private ItemTableSaveService itemTableSaveService;

    @GetMapping("/data/{metaType}")
    public Mono<ResponseEntity<?>> callDataMetaType(@PathVariable String metaType) {
        ReqApiDto request = new ReqApiDto();
        if (metaType != null) request.setPathVariable("/" + metaType);
        request.setMethod(GET);
        String endpoint = baseUrl + DATA;
        if (metaType != null) endpoint += "/" + metaType;

        return eternalReturnService.callApi(endpoint, request, JsonNode.class)
                .map(ResponseEntity::ok);
    }

    /// Simple UserNum Checking Method
    @GetMapping("/user/nickname")
    public Mono<?> callUserByNickname(@ModelAttribute ReqUserNicknameDto userNicknameDto) {
        ReqApiDto request = new ReqApiDto();
        request.setMethod(GET);
        request.setQueryParams(QueryParamUtils.convertToQueryParams(userNicknameDto));
        String endpoint = baseUrl + USER_NICKNAME;

        return eternalReturnService.callApi(endpoint, request, JsonNode.class)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/weapon_type_info")
    public Mono<?> callWeaponTypeInfo() {
        return experimentTableSaveService.callWeaponTypeInfo();
    }

    @GetMapping("/experiment")
    public Mono<?> callExperiment() {
        return experimentTableSaveService.callExperiment();
    }

    @GetMapping("/experiment_attribute")
    public Mono<?> callExperimentAttribute() {
        return experimentTableSaveService.callExperimentAttribute();
    }

    @GetMapping("/experiment_exp")
    public Mono<?> callExperimentExp() {
        return experimentTableSaveService.callExperimentExp();
    }

    @GetMapping("/experiment_mastery")
    public Mono<?> callExperimentMastery() {
        return experimentTableSaveService.callExperimentMastery();
    }

    @GetMapping("/experiment_level_up_stat")
    public Mono<?> callExperimentLevelUpStat() {
        return experimentTableSaveService.callExperimentLevelUpStat();
    }

    @GetMapping("/item_material")
    public Mono<?> callItemMaterial() {
        return itemTableSaveService.callItemMaterial();
    }

    @GetMapping("/item_consumable")
    public Mono<?> callItemConsumable() {
        return itemTableSaveService.callItemConsumable();
    }

    @GetMapping("/item_armor")
    public Mono<?> callItemArmor() {
        return itemTableSaveService.callItemArmor();
    }

    @GetMapping("/item_weapon")
    public Mono<?> callItemWeapon() {
        return itemTableSaveService.callItemWeapon();
    }

    @GetMapping("/item_special")
    public Mono<?> callItemSpecial() {
        return itemTableSaveService.callItemSpecial();
    }

    @GetMapping("/item_spawn")
    public Mono<?> callItemSpawn() {
        return itemTableSaveService.callItemSpawn();
    }

    ///  tag 별로 조회 가능한 item list 조회할 때 사용할 Entity
    @GetMapping("/item_search_option_v2")
    public Mono<?> callItemSearchOptionV2() {
        return itemTableSaveService.callItemSearchOptionV2();
    }


}
