package demo.eternalreturn.infrastructure.proxy.controller;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import demo.eternalreturn.infrastructure.proxy.service.ProxyService;
import demo.eternalreturn.infrastructure.proxy.service.experiment.ExperimentTableSaveService;
import demo.eternalreturn.infrastructure.proxy.service.item.ItemTableSaveService;
import demo.eternalreturn.infrastructure.proxy.service.l10n.L10NService;
import demo.eternalreturn.infrastructure.proxy.service.user.UserTableSaveService;
import demo.eternalreturn.infrastructure.proxy.service.util.QueryParamUtils;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqUserNicknameDto;
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
@RequestMapping("/api/v1/proxy")
public class ProxyController {

    @Value("${eternal-return.url}")
    private String baseUrl;

    @Autowired
    private final ProxyService eternalReturnService;
    @Autowired
    private final ExperimentTableSaveService experimentTableSaveService;
    @Autowired
    private final ItemTableSaveService itemTableSaveService;
    @Autowired
    private final UserTableSaveService userTableSaveService;
    @Autowired
    private final L10NService l10NService;

    @GetMapping("/data/{metaType}")
    public Mono<ResponseEntity<?>> callDataMetaType(@PathVariable String metaType) {
        ReqApiDto request = new ReqApiDto();
        if (metaType != null) request.setPathVariable("/" + metaType);
        request.setMethod(GET);
        String endpoint = baseUrl + DATA + (metaType != null ? "/" + metaType : "/hash");

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

    ///  top rank ( by season )
    ///  2025.03.27 ~ 06.26 09:00 SeasonId = 31
    @GetMapping("/rank/{season}")
    public Mono<?> callTopRankBySeason(@PathVariable String season) {
        return userTableSaveService.callRank1000BySeason(season);
    }

    ///  userRank ( by season )
    @GetMapping("/rank/{userNum}/{season}")
    public Mono<?> callUserRankByUserNumAndSeason(@PathVariable String userNum, @PathVariable String season) {
        return userTableSaveService.callUserRankByUserNumAndSeason(userNum, season);
    }

    ///  Season 정보 업데이트
    @GetMapping("/season")
    public Mono<?> callSeason() {
        return userTableSaveService.callSeason();
    }

    ///  L10N ( 언어를 바탕으로 게임 정보 가져오기, language = "Korean" )
    @GetMapping("/l10n/{language}")
    public Mono<?> callL10n(@PathVariable String language) {
        return l10NService.callL10n(language);
    }

}
