package demo.eternalreturn.presentation.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResSignInDto {

    private Long memberId;
    private String username;
    private String accessToken;
    private String refreshToken;
    private String profileImageUrl;

}
