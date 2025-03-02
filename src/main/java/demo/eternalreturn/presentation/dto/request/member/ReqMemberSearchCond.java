package demo.eternalreturn.presentation.dto.request.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqMemberSearchCond {

    private Long id;
    private String loginId;
    private String username;
    private Boolean isAdmin;
    private Boolean isDelete;

}
