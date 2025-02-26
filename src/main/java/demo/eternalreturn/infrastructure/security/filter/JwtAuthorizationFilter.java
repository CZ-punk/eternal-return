package demo.eternalreturn.infrastructure.security.filter;

import demo.eternalreturn.infrastructure.security.jwt.JwtTokenProvider;
import demo.eternalreturn.infrastructure.security.user.UserDetailsProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static demo.eternalreturn.infrastructure.security.jwt.JwtTokenProvider.BEARER_PREFIX;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtTokenProvider jwtUtils;
    @Autowired
    private final UserDetailsProvider userDetailsProvider;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            if (!request.getMethod().equals(HttpMethod.OPTIONS.name())) {
                String jwtToken = jwtUtils.resolveToken(request);

                if (StringUtils.hasText(jwtToken)) {
                    if (jwtToken.startsWith(BEARER_PREFIX)) {
                        String token = jwtToken.substring(BEARER_PREFIX.length()).trim();

                        if (jwtUtils.validationToken(token)) {

                            Authentication authentication = userDetailsProvider.getAuthentication(token);
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }
            }

            filterChain.doFilter(request, response);
        }

}
