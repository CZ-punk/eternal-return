package demo.eternalreturn.domain.constant;

import lombok.Getter;

@Getter
public enum Role {
    USER(Authority.USER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    Role(String role) {
        authority = role;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
