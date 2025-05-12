package demo.eternalreturn.presentation.dto.request.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqCreateCommentDto {

    private Long boardId;
    private String contents;

}
