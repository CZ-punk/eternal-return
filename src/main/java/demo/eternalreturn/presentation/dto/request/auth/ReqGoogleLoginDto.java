package demo.eternalreturn.presentation.dto.request.auth;

import lombok.Data;

@Data
public class ReqGoogleLoginDto {

    private String code;
    private String state;
}
