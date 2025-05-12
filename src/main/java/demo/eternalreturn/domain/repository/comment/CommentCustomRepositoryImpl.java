package demo.eternalreturn.domain.repository.comment;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.eternalreturn.domain.model.comment.Comment;
import demo.eternalreturn.presentation.dto.response.comment.ResCommentDetailsDto;
import demo.eternalreturn.presentation.dto.response.comment.ResMyCommentDto;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static demo.eternalreturn.domain.model.comment.QComment.comment;
import static demo.eternalreturn.presentation.dto.response.comment.ResCommentDetailsDto.*;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    @Transactional(readOnly = true)
    public Page<ResMyCommentDto> getMyComment(Long memberId, Pageable pageable) {
        List<ResMyCommentDto> content = queryFactory.select(comment)
                .from(comment)
                .leftJoin(comment.member)
                .on(comment.member.id.eq(memberId))
                .leftJoin(comment.board)
                .on(comment.board.member.id.eq(memberId))
                .where(comment.isDelete.isFalse())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream()
                .map(ResMyCommentDto::toDto).toList();

        JPAQuery<Long> countQuery = queryFactory.select(comment.count())
                .from(comment)
                .leftJoin(comment.member)
                .leftJoin(comment.board)
                .on(comment.member.id.eq(memberId))
                .on(comment.board.member.id.eq(memberId))
                .where(comment.isDelete.isFalse());

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);

    }

    @Override
    public Page<ResCommentDetailsDto> getCommentDetails(Long commentId, Pageable pageable) {
        // TODO: Test 이후 대댓글인 pk 에 대해서도 정상적으로 동작하는지 봐야함.

//        Comment parentComment = queryFactory
//                .select(comment)
//                .from(comment)
//                .where(comment.isDelete.isFalse())
//                .where(comment.id.eq(commentId))
//                .fetchOne();
//
//        if (parentComment == null) throw new CustomException(HttpStatus.NOT_FOUND, "not found comment");
//
//         queryFactory
//                .select(comment)
//                .from(comment)
//                .where(comment.isDelete.isFalse())
//                .where(comment.parent.id.eq(commentId))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch().stream()
//                 .map(comment -> ResCommentDetailsDto.RecommentDetailsDto.toDto(comment))
//
//
//
//
//        ResCommentDetailsDto commentDetails = new ResCommentDetailsDto(parentComment, replies);
//
//        // 결과를 리스트로 감싸서 반환
//        return new PageImpl<>(Collections.singletonList(commentDetails), pageable, 1);
//
//        queryFactory.select(comment)
//                .from(comment)
//                .leftJoin(comment.replies)
//                .on(comment.id.eq(comment.replies.))
//                .where(comment.isDelete.isFalse())
        return null;
    }
}
