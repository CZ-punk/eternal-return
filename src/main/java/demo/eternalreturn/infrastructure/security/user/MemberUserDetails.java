package demo.eternalreturn.infrastructure.security.user;

import demo.eternalreturn.domain.model.Member;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUserDetails extends CustomUserDetails {

    private Long memberId;
    private String loginId;
    private String loginPw;
    private String username;
    private String refreshToken;
    //    private Boolean isQuited;

    public static MemberUserDetails of(Member member) {
        MemberUserDetails memberUserDetails = MemberUserDetails.builder()
                .memberId(member.getId())
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .username(member.getUsername())
                .refreshToken(member.getRefreshToken())
                .build();

        memberUserDetails.roles = member.getRoles();

        return memberUserDetails;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return loginPw;
    }

}
