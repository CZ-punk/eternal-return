package demo.eternalreturn.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCharacterStats is a Querydsl query type for CharacterStats
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCharacterStats extends EntityPathBase<CharacterStats> {

    private static final long serialVersionUID = 605050905L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCharacterStats characterStats = new QCharacterStats("characterStats");

    public final NumberPath<Double> averageRank = createNumber("averageRank", Double.class);

    public final NumberPath<Integer> characterCode = createNumber("characterCode", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxKillings = createNumber("maxKillings", Integer.class);

    public final NumberPath<Integer> top3 = createNumber("top3", Integer.class);

    public final NumberPath<Double> top3Rate = createNumber("top3Rate", Double.class);

    public final NumberPath<Integer> totalGames = createNumber("totalGames", Integer.class);

    public final NumberPath<Integer> usages = createNumber("usages", Integer.class);

    public final QUserStats userStats;

    public final NumberPath<Integer> wins = createNumber("wins", Integer.class);

    public QCharacterStats(String variable) {
        this(CharacterStats.class, forVariable(variable), INITS);
    }

    public QCharacterStats(Path<? extends CharacterStats> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCharacterStats(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCharacterStats(PathMetadata metadata, PathInits inits) {
        this(CharacterStats.class, metadata, inits);
    }

    public QCharacterStats(Class<? extends CharacterStats> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userStats = inits.isInitialized("userStats") ? new QUserStats(forProperty("userStats")) : null;
    }

}

