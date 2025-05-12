package demo.eternalreturn.domain.repository.board;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.board.ReqBoardSearchCond;
import demo.eternalreturn.presentation.dto.response.board.ResBoardSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static demo.eternalreturn.domain.model.Board.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    @Transactional(readOnly = true)
    public Page<ResBoardSearchDto> getBoardPageListForUser(ReqBoardSearchCond cond, Pageable pageable) {
        List<ResBoardSearchDto> content = getContent(cond, false, pageable);
        JPAQuery<Long> countQuery = getCountQuery(cond, false);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResBoardSearchDto> getBoardPageListForAdmin(ReqBoardSearchCond cond, Pageable pageable) {
        List<ResBoardSearchDto> content = getContent(cond, true, pageable);
        JPAQuery<Long> countQuery = getCountQuery(cond, true);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private List<ResBoardSearchDto> getContent(ReqBoardSearchCond cond, Boolean isDeleted, Pageable pageable) {
        /// ROLE_USER 는 isDeleted == False
        /// ROLE_ADMIN 은 isDeleted == True
        return queryFactory
                .select(board)
                .from(board)
                .where(
                        condLikeTitle(cond.getTitle()),
                        condLikeContents(cond.getContents()),
                        condLikeAuthor(cond.getAuthor()),
                        containIsDelete(isDeleted)
                )
                .leftJoin(board.member).fetchJoin()
                .orderBy(getOrderBy(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream().map(ResBoardSearchDto::toDto).toList();
    }

    private JPAQuery<Long> getCountQuery(ReqBoardSearchCond cond, Boolean isDeleted) {
        return queryFactory
                .select(board.count())
                .from(board)
                .leftJoin(board.member).fetchJoin()
                .where(
                        condLikeTitle(cond.getTitle()),
                        condLikeContents(cond.getContents()),
                        condLikeAuthor(cond.getAuthor()),
                        containIsDelete(isDeleted)
                );
    }

    private BooleanExpression condLikeAuthor(String author) {
        return author != null ? board.author.contains(author) : null;
    }

    private BooleanExpression condLikeTitle(String title) {
        return title != null ? board.title.contains(title) : null;
    }

    private BooleanExpression condLikeContents(String contents) {
        return contents != null ? board.contents.contains(contents) : null;
    }

    private BooleanExpression containIsDelete(Boolean isDelete) {
        return isDelete ? null : board.isDelete.isFalse();
    }

    private OrderSpecifier<?>[] getOrderBy(Sort sort) {
        return sort.stream()
                .map(order -> {
                    if (order.getProperty().equals("title")) {
                        return order.isAscending() ? board.title.asc() : board.title.desc();
                    } else if (order.getProperty().equals("contents")) {
                        return order.isAscending() ? board.contents.asc() : board.contents.desc();
                    } else if (order.getProperty().equals("author")) {
                        return order.isAscending() ? board.author.asc() : board.author.desc();
                    } else if (order.getProperty().equals("createdAt")) {
                        return order.isAscending() ? board.createdAt.asc() : board.createdAt.desc();
                    } else if (order.getProperty().equals("updatedAt")) {
                        return order.isAscending() ? board.updatedAt.asc() : board.updatedAt.desc();
                    } else if (order.getProperty().equals("deletedAt")) {
                        return order.isAscending() ? board.deletedAt.asc() : board.deletedAt.desc();
                    }

                    return null;
                })
                .filter(Objects::nonNull)
                .toArray(OrderSpecifier[]::new);
    }
}
