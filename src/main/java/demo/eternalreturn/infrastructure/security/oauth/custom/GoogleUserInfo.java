package demo.eternalreturn.infrastructure.security.oauth.custom;

import lombok.AllArgsConstructor;

import java.util.Map;

import static demo.eternalreturn.domain.constant.SocialProvider.GOOGLE;

@AllArgsConstructor
public class GoogleUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    @Override
    public String getProviderId() {
        return attributes.get("sub").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getProvider() {
        return GOOGLE.getProvider();
    }

    @Override
    public String getProfileImageUrl() {
        return attributes.get("picture").toString();
    }
}
