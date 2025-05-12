package demo.eternalreturn.infrastructure.security.oauth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("OAuth2 Authentication failure", exception);

        String errorMessage = exception.getMessage();
        String redirectUrl = "http://localhost:5173/login/oauth2/callback?error=" + errorMessage;
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);

//        super.onAuthenticationFailure(request, response, exception);
    }
}