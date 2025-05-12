package demo.eternalreturn.infrastructure.security.user;

import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.model.Member.MemberRole;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import lombok.*;

import java.util.Map;
import java.util.stream.Collectors;

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
    private Boolean isDelete;

    public static MemberUserDetails of(Member member) {
        MemberUserDetails memberUserDetails = MemberUserDetails.builder()
                .memberId(member.getId())
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .username(member.getUsername())
                .refreshToken(member.getRefreshToken())
                .build();

        memberUserDetails.roles = member.getRoles().stream().map(MemberRole::getRole).collect(Collectors.toSet());

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

    @Override
    public String getName() {
        return username;
    }
}
