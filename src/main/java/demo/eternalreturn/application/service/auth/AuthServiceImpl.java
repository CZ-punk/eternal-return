package demo.eternalreturn.application.service.auth;

import demo.eternalreturn.domain.constant.Role;
import demo.eternalreturn.domain.model.Member;
import demo.eternalreturn.domain.repository.MemberRepository;
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

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

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
        Member duplicatedUser = memberRepository.findByLoginIdOrUsername(
                        reqSignUpDto.getLoginId(),
                        reqSignUpDto.getUsername())
                .orElse(null);

        if (duplicatedUser != null)
            throw new CustomException(HttpStatus.CONFLICT, "duplicated loginId or username");

        Member createdUser = Member.builder()
                .loginId(reqSignUpDto.getLoginId())
                .loginPw(passwordEncoder.encode(reqSignUpDto.getLoginPw()))
                .username(reqSignUpDto.getUsername())
                .roles(new HashSet<>(Collections.singleton(Role.USER)))
                .build();

        memberRepository.save(createdUser);

        return ResSignUpDto.builder()
                .loginId(createdUser.getLoginId())
                .username(createdUser.getUsername())
                .build();
    }

    @Override
    @Transactional
    public ResSignInDto signIn(ReqSignInDto reqSignInDto) {
        Member user = memberRepository.findByLoginId(reqSignInDto.getLoginId())
                .orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "not found member"));

        if (!passwordEncoder.matches(reqSignInDto.getLoginPw(), user.getLoginPw()))
            throw new CustomException(HttpStatus.UNAUTHORIZED, "no matches password");

        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(), user.getRoles());

        user.setRefreshToken(refreshToken);
        memberRepository.save(user);

        return ResSignInDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
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
            String refreshToken = jwtTokenProvider.resolveToken(request);
            if (StringUtils.hasText(refreshToken) && refreshToken.startsWith(BEARER_PREFIX))
                refreshToken = refreshToken.substring(BEARER_PREFIX.length()).trim();

            if (refreshToken == null) throw new CustomException(HttpStatus.BAD_REQUEST, "no refresh token");

            if (user.getMemberId() == null) throw new CustomException(HttpStatus.FORBIDDEN, "no authority");

            Member member = memberRepository.findById(user.getMemberId())
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "no exist member"));

            // context 저장된 refresh token 과 비교
            if (!memberUserDetailsService.checkCurrentTokenEquals(refreshToken, MemberUserDetails.of(member)))
                throw new CustomException(HttpStatus.UNAUTHORIZED, "invalid refresh token");

            String accessToken = jwtTokenProvider.createAccessToken(user.getMemberId(), user.getRoles());
            refreshToken = jwtTokenProvider.createRefreshToken(user.getMemberId(), user.getRoles());

            member.setRefreshToken(refreshToken);
            memberRepository.save(member);

            return ResRefreshDto.builder()
                    .id(member.getId())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .roles(member.getRoles())
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
