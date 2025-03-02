package demo.eternalreturn.infrastructure.init;

import demo.eternalreturn.domain.constant.Role;
import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.model.Member.MemberRole;
import demo.eternalreturn.domain.repository.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitAdminSetup {


    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void init() {
        if (memberRepository.findByLoginId("admin").isEmpty()) {
            MemberRole role = MemberRole.builder()
                    .role(Role.ADMIN)
                    .build();

            Member adminMember = Member.builder()
                    .loginId("admin")
                    .loginPw(passwordEncoder.encode("admin"))
                    .username("admin")
                    .build();


            adminMember.connectionRole(role);
            memberRepository.save(adminMember);
        }

        if (memberRepository.findByLoginId("member1").isEmpty()) {

            List<Member> memberList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                MemberRole role = MemberRole.builder()
                        .role(Role.USER)
                        .build();

                Member member = Member.builder()
                        .loginId("member" + i)
                        .loginPw(passwordEncoder.encode("member"))
                        .username("member" + i)
                        .build();
                member.connectionRole(role);
                memberList.add(member);
            }

            memberRepository.saveAll(memberList);
        }
    }
}
