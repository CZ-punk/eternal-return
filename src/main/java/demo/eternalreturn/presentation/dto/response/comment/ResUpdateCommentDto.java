package demo.eternalreturn.presentation.dto.response.comment;

import demo.eternalreturn.domain.model.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResUpdateCommentDto {
    private Long commentId;
    private String contents;
    private String author;
    private String authorProfileImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ResUpdateCommentDto toDto(Comment comment) {
        return ResUpdateCommentDto.builder()
                .commentId(comment.getId())
                .contents(comment.getContents())
                .author(comment.getMember().getUsername())
                .authorProfileImageUrl(comment.getMember().getProfileImageUrl())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
