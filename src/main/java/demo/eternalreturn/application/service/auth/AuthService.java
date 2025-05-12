package demo.eternalreturn.application.service.auth;

import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.auth.ReqSignInDto;
import demo.eternalreturn.presentation.dto.request.auth.ReqSignUpDto;
import demo.eternalreturn.presentation.dto.response.auth.ResRefreshDto;
import demo.eternalreturn.presentation.dto.response.auth.ResSignInDto;
import demo.eternalreturn.presentation.dto.response.auth.ResSignUpDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    ResSignUpDto signUp(ReqSignUpDto reqSignUpDto);

    ResSignInDto signIn(ReqSignInDto reqSignInDto);

    void signOut();

    ResRefreshDto recreateAccessToken(HttpServletRequest request);

}
