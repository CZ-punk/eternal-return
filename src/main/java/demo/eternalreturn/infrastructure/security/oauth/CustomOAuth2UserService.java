package demo.eternalreturn.infrastructure.security.oauth;

import demo.eternalreturn.domain.constant.Role;
import demo.eternalreturn.domain.constant.SocialProvider;
import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.model.Member.MemberRole;
import demo.eternalreturn.domain.repository.member.MemberRepository;
import demo.eternalreturn.infrastructure.security.oauth.custom.OAuth2UserInfo;
import demo.eternalreturn.infrastructure.security.oauth.custom.OAuth2UserProvider;
import demo.eternalreturn.infrastructure.security.oauth.handler.OAuth2FailureHandler;
import demo.eternalreturn.presentation.exception.CustomException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final OAuth2FailureHandler oAuth2FailureHandler;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        SocialProvider socialProvider = SocialProvider.from(provider);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserProvider.getOAuth2UserInfo(socialProvider, attributes);
        String loginId = oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId();

        Member member = memberRepository.findByLoginId(loginId).orElse(null);
        if (member == null) {
            MemberRole role = MemberRole.builder().role(Role.USER).build();
            member = Member.builder()
                    .loginId(loginId)
                    .profileImageUrl(oAuth2UserInfo.getProfileImageUrl())
                    .username(oAuth2UserInfo.getName())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .provider(socialProvider)
                    .providerEmail(oAuth2UserInfo.getEmail())
                    .build();
            member.connectionRole(role);
            memberRepository.save(member);
        } else if (member.isDelete()) {
            throw new AuthenticationException("Member is deleted") {};
        }

        return OAuth2UserDetails.of(member, attributes);
    }
}
