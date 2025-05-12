package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.service.comment.CommentService;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.comment.ReqCreateCommentDto;
import demo.eternalreturn.presentation.dto.request.comment.ReqCreateRecommentDto;
import demo.eternalreturn.presentation.dto.request.comment.ReqUpdateCommentDto;
import demo.eternalreturn.presentation.dto.response.PageResponseDto;
import demo.eternalreturn.presentation.dto.response.ResponseDto;
import demo.eternalreturn.presentation.dto.response.comment.*;
import demo.eternalreturn.presentation.exception.ResultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static demo.eternalreturn.presentation.exception.ResultMessage.*;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> createComment(
            @RequestBody ReqCreateCommentDto reqCreateCommentDto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        ///  댓글 작성
        ResCreateCommentDto resCreateCommentDto = commentService.createComment(reqCreateCommentDto, userDetails.getMemberId());
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resCreateCommentDto));
    }

    @PostMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> createRecomment(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long commentId,
            @RequestBody ReqCreateRecommentDto reqCreateRecommentDto
    ) {
        ///  대댓글 작성
        ResCreateRecomment resCreateRecomment = commentService.createRecomment(reqCreateRecommentDto, commentId, userDetails.getMemberId());
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resCreateRecomment));
    }

    @PatchMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> updateCommentById(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long commentId,
            @RequestBody ReqUpdateCommentDto reqCreateCommentDto
    ) {
        ///  댓글 수정
        ResUpdateCommentDto resUpdateCommentDto = commentService.updateCommentById(userDetails.getMemberId(), commentId, reqCreateCommentDto);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resUpdateCommentDto));
    }

    @GetMapping("/my-comments")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getMyComment(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PageableDefault Pageable pageable
    ) {
        ///  본인이 작성한 댓글 paging 처리로 가져옴
        Page<ResMyCommentDto> resMyCommentsPage  = commentService.getMyComment(userDetails.getMemberId(), pageable);
        PageResponseDto<ResMyCommentDto> page = new PageResponseDto<>(resMyCommentsPage);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, page));
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> deleteCommentById(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long commentId
    ) {
        commentService.deleteCommentById(commentId, userDetails);
        return ResponseEntity.ok(new ResponseDto<>(NO_CONTENT, Success, null));
    }

    @GetMapping("/{commentId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getCommentDetails(
            @PathVariable Long commentId,
            @PageableDefault Pageable pageable
    ) {
        ///  댓글 상세정보 ( 대댓글은 Paging 처리 )
        ResCommentDetailsDto resCommentDetailsDto = commentService.getCommentDetails(commentId, pageable);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resCommentDetailsDto));
    }

    // TODO: 특정 Board 에 대해서 Paging 처리로 Board, Comment data 가져오는 API
}
