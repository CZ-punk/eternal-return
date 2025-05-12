package demo.eternalreturn.infrastructure.security.oauth.handler;

import demo.eternalreturn.domain.constant.Role;
import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.repository.member.MemberRepository;
import demo.eternalreturn.infrastructure.security.jwt.JwtTokenProvider;
import demo.eternalreturn.infrastructure.security.oauth.OAuth2UserDetails;
import demo.eternalreturn.presentation.exception.CustomException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static demo.eternalreturn.infrastructure.security.jwt.JwtTokenProvider.BEARER_PREFIX;
import static java.net.URLEncoder.encode;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2UserDetails oAuth2UserDetails = (OAuth2UserDetails) authentication.getPrincipal();

        String loginId = oAuth2UserDetails.getLoginId();
        log.info("loginId: {}", oAuth2UserDetails.getLoginId());

        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "oauth2 success? but not found member.."));

        Long memberId = oAuth2UserDetails.getMemberId();
        String username = oAuth2UserDetails.getUsername();
        Set<Role> roles = oAuth2UserDetails.getRoles();

        String accessToken = jwtTokenProvider.createAccessToken(memberId, roles);
        String refreshToken = jwtTokenProvider.createRefreshToken(memberId, roles);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");

        // HTTP Only 쿠키 설정
        response.addCookie(createCookie("accessToken", accessToken));
        response.addCookie(createCookie("refreshToken", refreshToken));
        response.addCookie(createCookie("id", encode(memberId.toString(), StandardCharsets.UTF_8)));
        response.addCookie(createCookie("name", encode(username, StandardCharsets.UTF_8) ));
        response.addCookie(createCookie("profileImageUrl", encode(member.getProfileImageUrl(), StandardCharsets.UTF_8)));

        // 리디렉션 처리
        String redirectUrl = "http://localhost:5173/login/oauth2/callback";
        response.sendRedirect(redirectUrl);
    }

    private Cookie createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(false);
        // TODO: setSecure 배포시 true 수정
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        return cookie;
    }
}