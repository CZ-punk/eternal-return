package demo.eternalreturn.infrastructure.security.user;

import demo.eternalreturn.domain.constant.Role;
import demo.eternalreturn.infrastructure.security.jwt.JwtTokenProvider;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsProvider {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberUserDetailsService memberUserDetailsService;

    public Authentication getAuthentication(String jwtToken) {
        Claims claims = jwtTokenProvider.getClaims(jwtToken);
        String id = claims.getSubject();
        String roles = claims.get("role", String.class);

        if (roles == null) return null;

        boolean hasValidRole = false;
        String[] split = roles.split(",");

        for (String role : split) {
            log.info("role: {}", role);
            if (Arrays.asList(Role.values()).stream().map(Role::getAuthority).anyMatch(role::equals)) {
                hasValidRole = true;
                break;
            }
        }

        if (!hasValidRole) return null;
        CustomUserDetails userDetails = (CustomUserDetails) memberUserDetailsService.loadUserByUsername(id);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    private CustomUserDetailsService determineServiceBasedOnRole(String role) {
        if (Arrays.asList(Role.values()).contains(role)) {
            return memberUserDetailsService;
        }
        return null;
    }
}
