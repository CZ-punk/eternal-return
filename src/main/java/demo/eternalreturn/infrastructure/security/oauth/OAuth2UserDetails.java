package demo.eternalreturn.infrastructure.security.oauth;

import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.model.Member.MemberRole;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Slf4j
public class OAuth2UserDetails extends CustomUserDetails {

    private Long memberId;
    private String loginId;
    private String loginPw;
    private String username;
    private String refreshToken;
    private Boolean isDelete;

    private String provider;
    private String providerId;
    private Map<String, Object> attributes;

    public static OAuth2UserDetails of(Member member, Map<String, Object> attributes) {
        OAuth2UserDetails oAuth2UserDetails = OAuth2UserDetails.builder()
                .memberId(member.getId())
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .provider(member.getProviderId())
                .providerId(member.getProviderId())
                .username(member.getUsername())
                .refreshToken(member.getRefreshToken())
                .attributes(attributes)
                .build();
        oAuth2UserDetails.roles = member.getRoles().stream().map(MemberRole::getRole).collect(Collectors.toSet());

        return oAuth2UserDetails;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return username;
    }


    @Override
    public String getPassword() {
        return "";
    }
}
