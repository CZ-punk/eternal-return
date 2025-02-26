package demo.eternalreturn.infrastructure.security.user.custom;

import org.springframework.security.core.userdetails.UserDetailsService;

public abstract class CustomUserDetailsService implements UserDetailsService {

    public abstract boolean checkCurrentTokenEquals(String token, CustomUserDetails customUserDetails);

    public abstract String getLoginId();
    public abstract String getCurrentUsername();




}
