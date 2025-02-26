package demo.eternalreturn.infrastructure.security.user;

import demo.eternalreturn.application.service.cache.CacheService;
import demo.eternalreturn.domain.model.Member;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUserDetailsService extends CustomUserDetailsService {

    private final CacheService cacheService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = cacheService.getMember(id);
        return MemberUserDetails.of(member);
    }

    @Override
    public boolean checkCurrentTokenEquals(String token, CustomUserDetails customUserDetails) {
        MemberUserDetails memberUserDetails = (MemberUserDetails) customUserDetails;

        // Compare Only RefreshToken
        return token.equals(((MemberUserDetails) customUserDetails).getRefreshToken());
    }

    @Override
    public String getLoginId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails){
            return userDetails.getLoginId();
        }
        return null;
    }

    @Override
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails){
            return userDetails.getUsername();
        }
        return null;
    }

}
