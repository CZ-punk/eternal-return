package demo.eternalreturn.presentation.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqSignInDto {

    private String loginId;
    private String loginPw;
}
