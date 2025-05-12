package demo.eternalreturn.infrastructure.security.oauth.custom;

import demo.eternalreturn.domain.constant.SocialProvider;
import demo.eternalreturn.presentation.exception.CustomException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class OAuth2UserProvider {
    public static OAuth2UserInfo getOAuth2UserInfo(SocialProvider socialProvider, Map<String, Object> attributes) {
        switch (socialProvider) {
            case KAKAO -> {
                return new KakaoUserInfo(attributes);
            }
            case NAVER -> {
                return new NaverUserInfo(attributes);
            }
            case GOOGLE -> {
                return new GoogleUserInfo(attributes);
            }
        }
        throw new CustomException(HttpStatus.BAD_REQUEST, "INVALID PROVIDER TYPE");
    }
}
