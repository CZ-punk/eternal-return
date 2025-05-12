package demo.eternalreturn.application.service.comment;

import demo.eternalreturn.domain.model.Board.Board;
import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.model.comment.Comment;
import demo.eternalreturn.domain.repository.board.BoardRepository;
import demo.eternalreturn.domain.repository.comment.CommentCustomRepository;
import demo.eternalreturn.domain.repository.comment.CommentRepository;
import demo.eternalreturn.domain.repository.member.MemberRepository;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.comment.ReqCreateCommentDto;
import demo.eternalreturn.presentation.dto.request.comment.ReqCreateRecommentDto;
import demo.eternalreturn.presentation.dto.request.comment.ReqUpdateCommentDto;
import demo.eternalreturn.presentation.dto.response.comment.*;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentCustomRepository commentCustomRepository;

    @Override
    @Transactional
    public ResCreateCommentDto createComment(ReqCreateCommentDto dto, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found member"));
        Board board = boardRepository.findById(dto.getBoardId()).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found board"));

        Comment comment = Comment.createComment(dto.getContents());
        comment.connectionMember(member);
        comment.connectionBoard(board);

        commentRepository.save(comment);

        return ResCreateCommentDto.toDto(comment);
    }

    @Override
    @Transactional
    public ResCreateRecomment createRecomment(ReqCreateRecommentDto dto, Long commentId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found member"));
        Comment parentComment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found comment"));
        Board board = parentComment.getBoard();

        if (parentComment.getParent() != null)
            throw new CustomException(HttpStatus.NOT_ACCEPTABLE, "Comments cannot be written on replies");

        Comment recomment = Comment.createComment(dto.getContents());
        recomment.connectionMember(member);
        recomment.connectionBoard(board);
        parentComment.connectionRecomment(recomment);

        commentRepository.save(recomment);

        return ResCreateRecomment.toDto(recomment);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResMyCommentDto> getMyComment(Long memberId, Pageable pageable) {
        return commentCustomRepository.getMyComment(memberId, pageable);
    }


    @Override
    @Transactional
    public ResUpdateCommentDto updateCommentById(Long memberId, Long commentId, ReqUpdateCommentDto reqCreateCommentDto) {
        Comment comment = commentRepository.findByIdAndIsDeleteFalse(commentId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found comment"));
        if (!comment.getMember().getId().equals(memberId)) throw new CustomException(HttpStatus.FORBIDDEN, "not allowed to update comment");

        comment.updateComment(reqCreateCommentDto.getContents());
        commentRepository.save(comment);

        return ResUpdateCommentDto.toDto(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public ResCommentDetailsDto getCommentDetails(Long commentId, Pageable pageable) {
        commentCustomRepository.getCommentDetails(commentId, pageable);
        return null;
    }

    @Override
    @Transactional
    public void deleteCommentById(Long commentId, CustomUserDetails userDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found comment"));
        if (!comment.getMember().getId().equals(userDetails.getMemberId())) throw new CustomException(HttpStatus.FORBIDDEN, "not allowed to delete comment");
        comment.softDelete(userDetails.getUsername());
    }
}

