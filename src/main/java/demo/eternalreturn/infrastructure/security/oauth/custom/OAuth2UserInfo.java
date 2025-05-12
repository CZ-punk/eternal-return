package demo.eternalreturn.infrastructure.security.oauth.custom;

public interface OAuth2UserInfo {
    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
    String getProfileImageUrl();


}
