package demo.eternalreturn.domain.constant;

import lombok.Getter;

@Getter
public enum SocialProvider {

    GOOGLE("google"), NAVER("naver"), KAKAO("kakao");
//    GITHUB("github");

    private final String provider;

    SocialProvider(String provider) {
        this.provider = provider;
    }

    public static SocialProvider from(String provider) {
        for (SocialProvider socialProvider : SocialProvider.values()) {
            if (socialProvider.provider.equals(provider)) {
                return socialProvider;
            }
        }
        return null;
    }

}
