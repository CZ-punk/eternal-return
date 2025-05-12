package demo.eternalreturn.domain.repository.eternalreturn.player.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.eternalreturn.domain.model.eternal_return.user.CharacterStats;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static demo.eternalreturn.domain.model.eternal_return.user.QCharacterStats.characterStats;
import static demo.eternalreturn.domain.model.eternal_return.user.QTopRank.topRank;
import static demo.eternalreturn.domain.model.eternal_return.user.QUserStats.userStats;
import static demo.eternalreturn.presentation.dto.response.eternalreturn.user.ResTopRankDto.UserRankDto;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TopRankCustomRepositoryImpl implements TopRankCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserRankDto> searchTopRankPage(Pageable pageable) {
        try {
            List<UserRankDto> content = queryFactory
                    .select(
                            Projections.constructor(
                                    UserRankDto.class,
                                    topRank,
                                    userStats
                            )
                    )
                    .from(topRank)
                    .leftJoin(userStats)
                    .on(topRank.userNum.eq(userStats.userNum))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .orderBy(topRank.rank.asc())
                    .fetch();

            List<Integer> userNumList = content.stream().map(UserRankDto::getUserNum).toList();
            Map<Integer, List<CharacterStats>> characterStatsGroup = queryFactory.select(
                            characterStats
                    )
                    .from(characterStats)
                    .where(characterStats.userNum.in(userNumList))
                    .fetch()
                    .stream()
                    .collect(Collectors.groupingBy(CharacterStats::getUserNum));

            content.forEach(userRankDto -> userRankDto.connectMainExperiment(
                    new UserRankDto.MainExperimentDto(characterStatsGroup.get(userRankDto.getUserNum()))));

            JPAQuery<Long> countQuery = queryFactory
                    .select(userStats.count())
                    .from(userStats)
                    .leftJoin(topRank)
                    .on(userStats.userNum.eq(topRank.userNum));

            return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);

        } catch (Exception e) {
            log.error("Server Error: {}", e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
