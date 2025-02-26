package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.service.proxy.ProxyService;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqExperimentCodeDto;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqUserNicknameDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/custom")
public class ProxyController {

    @Autowired
    private final ProxyService proxyService;

    @GetMapping("/username")
    public ResponseEntity<?> searchUserInfoByUsername(@ModelAttribute ReqUserNicknameDto userNicknameDto) {
        return ResponseEntity.ok(proxyService.searchUserInfoByUsername(userNicknameDto));
    }

    @GetMapping("/experiment")
    public ResponseEntity<?> searchExperimentByExperimentCode(@ModelAttribute ReqExperimentCodeDto experimentCode) {
        return ResponseEntity.ok(proxyService.searchExperimentStatByExperimentCode(experimentCode));
    }


    // TODO: Item 관련 컨트롤러 생성 후, Item Type 별 목록 내려주는 Method 구현


}
