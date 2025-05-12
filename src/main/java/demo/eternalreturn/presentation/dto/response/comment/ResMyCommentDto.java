package demo.eternalreturn.presentation.dto.response.comment;

import demo.eternalreturn.domain.model.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResMyCommentDto {

    private Long boardId;
    private Long commentId;
    private String contents;

    private Integer likeCount;
    private LocalDateTime createdAt;

    public static ResMyCommentDto toDto(Comment comment) {
        return ResMyCommentDto.builder()
                .boardId(comment.getBoard().getId())
                .commentId(comment.getId())
                .contents(comment.getContents())
                .likeCount(comment.getLikeCount())
                .createdAt(comment.getCreatedAt())
                .build();

    }
}
