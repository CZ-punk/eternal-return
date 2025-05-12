package demo.eternalreturn.application.service.comment;

import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.comment.ReqCreateCommentDto;
import demo.eternalreturn.presentation.dto.request.comment.ReqCreateRecommentDto;
import demo.eternalreturn.presentation.dto.request.comment.ReqUpdateCommentDto;
import demo.eternalreturn.presentation.dto.response.comment.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    ResCreateCommentDto createComment(ReqCreateCommentDto reqCreateCommentDto, Long memberId);

    ResCreateRecomment createRecomment(ReqCreateRecommentDto reqCreateRecommentDto, Long commentId, Long memberId);

    Page<ResMyCommentDto> getMyComment(Long memberId, Pageable pageable);

    ResUpdateCommentDto updateCommentById(Long memberId, Long commentId, ReqUpdateCommentDto reqCreateCommentDto);

    void deleteCommentById(Long commentId, CustomUserDetails userDetails);

    ResCommentDetailsDto getCommentDetails(Long commentId, Pageable pageable);
}
