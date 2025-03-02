package demo.eternalreturn.infrastructure.config;

import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.isAuthenticated()) return Optional.empty();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return Optional.ofNullable(userDetails.getUsername());
    }
}