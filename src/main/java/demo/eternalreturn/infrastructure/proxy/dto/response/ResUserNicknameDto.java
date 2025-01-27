package demo.eternalreturn.infrastructure.proxy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResUserNicknameDto {

    private Integer userNum;
    private String nickname;
}
