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
public class ResCreateCommentDto {

    private Long commentId;
    private String contents;
    private String author;
    private String authorProfileImageUrl;
    private LocalDateTime createdAt;

    public static ResCreateCommentDto toDto(Comment comment) {
        return ResCreateCommentDto.builder()
                .commentId(comment.getId())
                .contents(comment.getContents())
                .author(comment.getMember().getUsername())
                .authorProfileImageUrl(comment.getMember().getProfileImageUrl())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
