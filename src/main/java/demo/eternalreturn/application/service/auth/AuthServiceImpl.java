package demo.eternalreturn.application.service.auth;

import demo.eternalreturn.domain.constant.Role;
import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.model.Member.MemberRole;
import demo.eternalreturn.domain.repository.member.MemberRepository;
import demo.eternalreturn.infrastructure.security.jwt.JwtTokenProvider;
import demo.eternalreturn.infrastructure.security.user.MemberUserDetails;
import demo.eternalreturn.infrastructure.security.user.MemberUserDetailsService;
import demo.eternalreturn.presentation.dto.request.auth.ReqSignInDto;
import demo.eternalreturn.presentation.dto.request.auth.ReqSignUpDto;
import demo.eternalreturn.presentation.dto.response.auth.ResRefreshDto;
import demo.eternalreturn.presentation.dto.response.auth.ResSignInDto;
import demo.eternalreturn.presentation.dto.response.auth.ResSignUpDto;
import demo.eternalreturn.presentation.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static demo.eternalreturn.infrastructure.security.jwt.JwtTokenProvider.BEARER_PREFIX;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberUserDetailsService memberUserDetailsService;

    @Override
    @Transactional
    public ResSignUpDto signUp(ReqSignUpDto reqSignUpDto) {
        boolean isEmpty = memberRepository.findByLoginIdOrUsername(
                        reqSignUpDto.getLoginId(),
                        reqSignUpDto.getUsername())
                .isEmpty();

        if (!isEmpty) throw new CustomException(HttpStatus.CONFLICT, "duplicated loginId or username");

        MemberRole memberRole = MemberRole.builder().role(Role.USER).build();
        Member createdUser = Member.builder()
                .loginId(reqSignUpDto.getLoginId())
                .loginPw(passwordEncoder.encode(reqSignUpDto.getLoginPw()))
                .username(reqSignUpDto.getUsername())
                .build();
        createdUser.connectionRole(memberRole);

        memberRepository.save(createdUser);

        return ResSignUpDto.builder()
                .loginId(createdUser.getLoginId())
                .username(createdUser.getUsername())
                .build();
    }

    @Override
    @Transactional
    public ResSignInDto signIn(ReqSignInDto reqSignInDto) {
        Member member = memberRepository.findByLoginIdAndIsDeleteFalseWithRoles(reqSignInDto.getLoginId())
                .orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "not found member by loginId"));

        if (!passwordEncoder.matches(reqSignInDto.getLoginPw(), member.getLoginPw()))
            throw new CustomException(HttpStatus.UNAUTHORIZED, "no matches password");

        Set<Role> roles = member.getRoles().stream().map(MemberRole::getRole).collect(Collectors.toSet());
        String accessToken = jwtTokenProvider.createAccessToken(member.getId(), roles);
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getId(), roles);

        member.setRefreshToken(refreshToken);
        memberRepository.save(member);

        return ResSignInDto.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }

    @Override
    @Transactional
    public void signOut() {
        this.getUserDetailsByToken().ifPresentOrElse(
                user -> {
                    Member member = memberRepository.findById(user.getMemberId())
                            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not exist member"));

                    member.signOut();
                    memberRepository.save(member);
                },
                () -> {
                    throw new CustomException(HttpStatus.UNAUTHORIZED, "no authentication info");
                }
        );
    }

    @Override
    @Transactional
    public ResRefreshDto recreateAccessToken(HttpServletRequest request) {
        return this.getUserDetailsByToken().map(user -> {
            String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
            if (StringUtils.hasText(refreshToken) && refreshToken.startsWith(BEARER_PREFIX))
                refreshToken = refreshToken.substring(BEARER_PREFIX.length()).trim();

            if (refreshToken == null) throw new CustomException(HttpStatus.BAD_REQUEST, "no refresh token");

            if (user.getMemberId() == null) throw new CustomException(HttpStatus.FORBIDDEN, "no authority");

            Member member = memberRepository.findById(user.getMemberId())
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "no exist member"));

            if (!member.getLoginPw().equals(user.getLoginPw()))
                throw new CustomException(HttpStatus.UNAUTHORIZED, "no equals password");

            if (member.isDelete()) throw new CustomException(HttpStatus.FORBIDDEN, "with draw member");

            // context 저장된 refresh token 과 비교
            if (!memberUserDetailsService.checkCurrentTokenEquals(refreshToken, MemberUserDetails.of(member)))
                throw new CustomException(HttpStatus.UNAUTHORIZED, "invalid refresh token");

            String accessToken = jwtTokenProvider.createAccessToken(user.getMemberId(), user.getRoles());
            refreshToken = jwtTokenProvider.createRefreshToken(user.getMemberId(), user.getRoles());

            member.setRefreshToken(refreshToken);
            memberRepository.save(member);

            Set<Role> roles = member.getRoles().stream().map(MemberRole::getRole).collect(Collectors.toSet());
            return ResRefreshDto.builder()
                    .id(member.getId())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .roles(roles)
                    .build();

        }).orElseThrow(() -> new CustomException(HttpStatus.FORBIDDEN, "no authority"));
    }

    private Optional<MemberUserDetails> getUserDetailsByToken() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            MemberUserDetails userDetails = (MemberUserDetails) principal;
            return Optional.ofNullable(userDetails);

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
