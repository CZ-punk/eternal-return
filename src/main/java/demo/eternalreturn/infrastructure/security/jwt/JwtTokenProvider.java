package demo.eternalreturn.infrastructure.security.jwt;

import demo.eternalreturn.domain.constant.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${spring.application.name")
    private String issuer;
    @Value("${jwt.secret-key}")
    private String key;

    private final Long ACCESS_TOKEN_VALID_TIME = 5 * 60 * 1000L;    // ms
    private final Long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 1000L;  // ms
    private SecretKey secretKey;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(key));
    }

    public String createAccessToken(Long userId, Set<Role> roles) {
        StringBuilder role = new StringBuilder();

        roles.stream().map(Role::getAuthority).forEach(auth -> role.append(auth).append(","));
        role.deleteCharAt(role.length() - 1);

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("role", role.toString())
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_TIME))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public String createRefreshToken(Long userId, Set<Role> roles) {
        StringBuilder role = new StringBuilder();

        roles.stream().map(Role::getAuthority).forEach(auth -> role.append(auth).append(","));
        role.deleteCharAt(role.length() - 1);

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("role", role.toString())
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALID_TIME))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public boolean validationToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    public Claims getClaims(String token) {
        try {

            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }
}
