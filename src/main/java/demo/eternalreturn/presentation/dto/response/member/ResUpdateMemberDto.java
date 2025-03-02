package demo.eternalreturn.presentation.dto.response.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResUpdateMemberDto {

    private Long id;
    private String username;

}
