package demo.eternalreturn.presentation.dto.response.comment;

import demo.eternalreturn.domain.model.comment.Comment;
import demo.eternalreturn.presentation.dto.response.PageResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResCommentDetailsDto {
    private Long boardId;
    private Long commentId;
    private String contents;
    private String author;
    private String authorProfileImageUrl;
    private Integer likeCount;
    private Integer replyCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private PageResponseDto<RecommentDetailsDto> recommentList;

    @Data
    @Builder
    public static class RecommentDetailsDto {

        private Long recommentId;
        private String contents;
        private String author;
        private String authorProfileImageUrl;
        private Integer likeCount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static RecommentDetailsDto toDto(Comment comment) {
            return RecommentDetailsDto.builder()
                    .recommentId(comment.getId())
                    .contents(comment.getContents())
//                    .author(comment)
                    .build();

            /// Member Repository 에서 author + profileImageurl 도 가져와야함.
        }
    }
}
