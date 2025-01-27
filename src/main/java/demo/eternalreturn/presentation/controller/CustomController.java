package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.service.CustomService;
import demo.eternalreturn.presentation.controller.dto.response.ResponseDto;
import demo.eternalreturn.presentation.controller.dto.request.ReqUserNicknameDto;
import demo.eternalreturn.presentation.controller.dto.request.ReqExperimentCodeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/custom")
public class CustomController {

    @Autowired
    private final CustomService customService;

    @GetMapping("/username")
    public ResponseDto<?> searchUserInfoByUsername(@ModelAttribute ReqUserNicknameDto userNicknameDto) {
        return customService.searchUserInfoByUsername(userNicknameDto);
    }

    @GetMapping("/experiment")
    public ResponseDto<?> searchExperimentByExperimentCode(@ModelAttribute ReqExperimentCodeDto experimentCode) {
        return customService.searchExperimentStatByExperimentCode(experimentCode);
    }
    
    // TODO: 추후 해당 Controller User Custom 부분으로 변경 예정
}
