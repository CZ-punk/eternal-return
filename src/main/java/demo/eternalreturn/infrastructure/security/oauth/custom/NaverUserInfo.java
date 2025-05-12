package demo.eternalreturn.infrastructure.security.oauth.custom;

import lombok.AllArgsConstructor;

import java.util.Map;

import static demo.eternalreturn.domain.constant.SocialProvider.NAVER;


public class NaverUserInfo implements OAuth2UserInfo {
    private Map<String, Object> response;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.response = (Map<String, Object>) attributes.get("response");
    }


    @Override
    public String getProfileImageUrl() {
        return response.get("profile_image").toString();
    }

    @Override
    public String getName() {
        return response.get("name").toString();
    }

    @Override
    public String getEmail() {
        return response.get("email").toString();
    }

    @Override
    public String getProviderId() {
        return response.get("id").toString();
    }

    @Override
    public String getProvider() {
        return NAVER.getProvider();
    }
}
