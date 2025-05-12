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
public class ResCreateRecomment {

    private Long commentId;
    private Long recommentId;
    private String contents;
    private String author;
    private String authorProfileImageUrl;
    private LocalDateTime createdAt;

    public static ResCreateRecomment toDto(Comment recomment) {
        return ResCreateRecomment.builder()
                .commentId(recomment.getParent().getId())
                .recommentId(recomment.getId())
                .contents(recomment.getContents())
                .author(recomment.getMember().getUsername())
                .authorProfileImageUrl(recomment.getMember().getProfileImageUrl())
                .createdAt(recomment.getCreatedAt())
                .build();
    }
}
