package demo.eternalreturn.domain.repository.member;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.eternalreturn.domain.constant.Role;
import demo.eternalreturn.presentation.dto.response.member.ResMemberSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static demo.eternalreturn.domain.model.Board.QBoard.board;
import static demo.eternalreturn.domain.model.Member.QMember.member;
import static demo.eternalreturn.domain.model.Member.QMemberRole.memberRole;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    @Autowired
    private final JPAQueryFactory queryFactory;


    @Override
    public Page<ResMemberSearchDto> searchMember(Long id, String loginId, String username, Boolean isAdmin, Boolean isDelete, Pageable pageable) {

        List<ResMemberSearchDto> content = queryFactory
                .select(member)
                .from(member)
                .where(
                        condEqMemberId(id),
                        condLikeLoginId(loginId),
                        condLikeUsername(username),
                        condIsAdmin(isAdmin),
                        condIsDelete(isDelete)
                )
                .leftJoin(member.roles, memberRole).fetchJoin()
                .leftJoin(member.boardList, board).fetchJoin()
                .orderBy(getOrderBy(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream().map(ResMemberSearchDto::toDto).toList();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member)
                .where(
                        condEqMemberId(id),
                        condLikeLoginId(loginId),
                        condLikeUsername(username),
                        condIsAdmin(isAdmin),
                        condIsDelete(isDelete)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression condEqMemberId(Long memberId) {
        return memberId != null ? member.id.eq(memberId) : null;
    }

    private BooleanExpression condLikeLoginId(String loginId) {
        return loginId != null ? member.loginId.contains(loginId) : null;
    }

    private BooleanExpression condLikeUsername(String username) {
        return username != null ? member.username.contains(username) : null;
    }

    private BooleanExpression condIsAdmin(Boolean isAdmin) {
        if (isAdmin == null) return null;
        return isAdmin ? memberRole.role.eq(Role.ADMIN) : memberRole.role.eq(Role.USER);
    }

    private BooleanExpression condIsDelete(Boolean isDelete) {
        if (isDelete == null) return member.isDelete.isFalse();
        return isDelete ? member.isDelete.isTrue() : member.isDelete.isFalse();
    }

    private OrderSpecifier<?>[] getOrderBy(Sort sort) {
        return sort.stream()
                .map(order -> {
                    if (order.getProperty().equals("memberId")) {
                        return order.isAscending() ? member.id.asc() : member.id.desc();
                    } else if (order.getProperty().equals("loginId")) {
                        return order.isAscending() ? member.loginId.asc() : member.loginId.desc();
                    } else if (order.getProperty().equals("username")) {
                        return order.isAscending() ? member.username.asc() : member.username.desc();
                    } else if (order.getProperty().equals("roles")) {
                        return order.isAscending() ? memberRole.role.asc() : memberRole.role.desc();
                    } else if (order.getProperty().equals("createdAt")) {
                        return order.isAscending() ? member.createdAt.asc() : member.createdAt.desc();
                    } else if (order.getProperty().equals("updatedAt")) {
                        return order.isAscending() ? member.updatedAt.asc() : member.updatedAt.desc();
                    } else if (order.getProperty().equals("deletedAt")) {
                        return order.isAscending() ? member.deletedAt.asc() : member.deletedAt.desc();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toArray(OrderSpecifier[]::new);
    }
}
