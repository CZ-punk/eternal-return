package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.CustomService;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqUserNicknameDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
