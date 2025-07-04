package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.service.auth.AuthService;
import demo.eternalreturn.presentation.dto.request.auth.ReqSignInDto;
import demo.eternalreturn.presentation.dto.request.auth.ReqSignUpDto;
import demo.eternalreturn.presentation.dto.response.ResponseDto;
import demo.eternalreturn.presentation.dto.response.auth.ResRefreshDto;
import demo.eternalreturn.presentation.dto.response.auth.ResSignInDto;
import demo.eternalreturn.presentation.dto.response.auth.ResSignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static demo.eternalreturn.presentation.exception.ResultMessage.Success;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody ReqSignUpDto reqSignUpDto) {
        ResSignUpDto resSignUpDto = authService.signUp(reqSignUpDto);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resSignUpDto));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody ReqSignInDto reqSignInDto) {
        ResSignInDto resSignInDto = authService.signIn(reqSignInDto);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resSignInDto));
    }

    @PostMapping("/sign-out")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> signOut() {
        authService.signOut();
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, null));
    }

    @PostMapping("/token/refresh")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> recreateAccessToken(HttpServletRequest request) {
        ResRefreshDto resRefreshDto = authService.recreateAccessToken(request);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resRefreshDto));
    }


}
