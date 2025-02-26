package demo.eternalreturn.presentation.dto.response.auth;

import demo.eternalreturn.domain.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResRefreshDto {

    private Long id;
    private String accessToken;
    private String refreshToken;
    private Set<Role> roles;
}
