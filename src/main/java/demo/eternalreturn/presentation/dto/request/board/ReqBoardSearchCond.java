package demo.eternalreturn.presentation.dto.request.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqBoardSearchCond {

    private Long id;
    private String title;
    private String contents;
    private String username;
}
