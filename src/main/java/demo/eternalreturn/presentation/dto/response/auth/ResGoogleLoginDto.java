package demo.eternalreturn.presentation.dto.response.auth;

import lombok.Data;

@Data
public class ResGoogleLoginDto {
    private String access_token;
    private String refresh_token;
    private int expires_in;
    private String token_type;
    private String scope;
}
