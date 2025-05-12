package demo.eternalreturn.domain.model.eternal_return.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserStats is a Querydsl query type for UserStats
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserStats extends EntityPathBase<UserStats> {

    private static final long serialVersionUID = 1044879722L;

    public static final QUserStats userStats = new QUserStats("userStats");

    public final NumberPath<Double> averageAssistants = createNumber("averageAssistants", Double.class);

    public final NumberPath<Double> averageHunts = createNumber("averageHunts", Double.class);

    public final NumberPath<Double> averageKills = createNumber("averageKills", Double.class);

    public final NumberPath<Double> averageRank = createNumber("averageRank", Double.class);

    public final NumberPath<Integer> escapeCount = createNumber("escapeCount", Integer.class);

    public final NumberPath<Integer> matchingMode = createNumber("matchingMode", Integer.class);

    public final NumberPath<Integer> matchingTeamMode = createNumber("matchingTeamMode", Integer.class);

    public final NumberPath<Integer> mmr = createNumber("mmr", Integer.class);

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    public final NumberPath<Double> rankPercent = createNumber("rankPercent", Double.class);

    public final NumberPath<Integer> rankSize = createNumber("rankSize", Integer.class);

    public final NumberPath<Integer> seasonId = createNumber("seasonId", Integer.class);

    public final NumberPath<Double> top1 = createNumber("top1", Double.class);

    public final NumberPath<Double> top2 = createNumber("top2", Double.class);

    public final NumberPath<Double> top3 = createNumber("top3", Double.class);

    public final NumberPath<Double> top5 = createNumber("top5", Double.class);

    public final NumberPath<Double> top7 = createNumber("top7", Double.class);

    public final NumberPath<Integer> totalDeaths = createNumber("totalDeaths", Integer.class);

    public final NumberPath<Integer> totalGames = createNumber("totalGames", Integer.class);

    public final NumberPath<Integer> totalTeamKills = createNumber("totalTeamKills", Integer.class);

    public final NumberPath<Integer> totalWins = createNumber("totalWins", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updateAt = createDateTime("updateAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> userNum = createNumber("userNum", Integer.class);

    public QUserStats(String variable) {
        super(UserStats.class, forVariable(variable));
    }

    public QUserStats(Path<? extends UserStats> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserStats(PathMetadata metadata) {
        super(UserStats.class, metadata);
    }

}

