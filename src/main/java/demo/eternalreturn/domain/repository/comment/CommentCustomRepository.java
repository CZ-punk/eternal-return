package demo.eternalreturn.domain.repository.comment;

import demo.eternalreturn.presentation.dto.response.comment.ResCommentDetailsDto;
import demo.eternalreturn.presentation.dto.response.comment.ResMyCommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentCustomRepository {

    Page<ResMyCommentDto> getMyComment(Long memberId, Pageable pageable);

    Page<ResCommentDetailsDto> getCommentDetails(Long commentId, Pageable pageable);
}
