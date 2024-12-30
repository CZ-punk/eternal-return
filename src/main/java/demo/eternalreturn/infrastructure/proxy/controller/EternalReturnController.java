package demo.eternalreturn.infrastructure.proxy.controller;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqUserNicknameDto;
import demo.eternalreturn.infrastructure.proxy.service.EternalReturnService;
import demo.eternalreturn.infrastructure.proxy.service.TableSaveService;
import demo.eternalreturn.infrastructure.utils.QueryParamUtils;
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
    private TableSaveService tableSaveService;

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

    @GetMapping("/user/nickname")
    public Mono<ResponseEntity<?>> callUserByNickname(@ModelAttribute ReqUserNicknameDto userNicknameDto) {
        ReqApiDto request = new ReqApiDto();
        request.setMethod(GET);
        request.setQueryParams(QueryParamUtils.convertToQueryParams(userNicknameDto));
        String endpoint = baseUrl + USER_NICKNAME;

        return eternalReturnService.callApi(endpoint, request, JsonNode.class)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/weapon_type_info")
    public ResponseEntity<?> callWeaponTypeInfo() {
        return tableSaveService.callWeaponTypeInfo();
    }


    @GetMapping("/experiment")
    public ResponseEntity<?> callExperiment() {
        return tableSaveService.callExperiment();
    }

    @GetMapping("/experiment_attribute")
    public ResponseEntity<?> callExperimentAttribute() {
        return tableSaveService.callExperimentAttribute();
    }




    @GetMapping("/rank/top/{seasonId}/{matchingTeamMode}")
    public Mono<ResponseEntity<?>> callRankTop(@PathVariable Long seasonId,
                                               @PathVariable Long matchingTeamMode) {

        // TODO: SEASON_ID = 17 까지 SOLO GAME 존재
        // TODO: 현재 이전 프리시즌 및 일부 시즌 데이터 없음

        return null;
    }



}
