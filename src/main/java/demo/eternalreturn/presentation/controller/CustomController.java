package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.CustomService;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqUserNicknameDto;
import demo.eternalreturn.presentation.controller.dto.request.ReqExperimentCodeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/custom")
public class CustomController {

    @Autowired
    private final CustomService customService;

    @GetMapping("/username")
    public ResponseEntity<?> searchUserInfoByUsername(@ModelAttribute ReqUserNicknameDto userNicknameDto) {
        return customService.searchUserInfoByUsername(userNicknameDto);
    }

    @GetMapping("/experiment")
    public ResponseEntity<?> searchExperimentByExperimentCode(@ModelAttribute ReqExperimentCodeDto experimentCode) {
        return customService.searchExperimentStatByExperimentCode(experimentCode);
    }
    
    
    // TODO: Item 관련 컨트롤러 생성 후, Item Type 별 목록 내려주는 Method 구현




}
