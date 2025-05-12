package demo.eternalreturn.domain.model.eternal_return.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCharacterStats is a Querydsl query type for CharacterStats
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCharacterStats extends EntityPathBase<CharacterStats> {

    private static final long serialVersionUID = 1127634656L;

    public static final QCharacterStats characterStats = new QCharacterStats("characterStats");

    public final NumberPath<Double> averageRank = createNumber("averageRank", Double.class);

    public final NumberPath<Integer> characterCode = createNumber("characterCode", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> maxKillings = createNumber("maxKillings", Integer.class);

    public final NumberPath<Double> top3 = createNumber("top3", Double.class);

    public final NumberPath<Double> top3Rate = createNumber("top3Rate", Double.class);

    public final NumberPath<Integer> totalGames = createNumber("totalGames", Integer.class);

    public final NumberPath<Double> usages = createNumber("usages", Double.class);

    public final NumberPath<Integer> userNum = createNumber("userNum", Integer.class);

    public final NumberPath<Double> wins = createNumber("wins", Double.class);

    public QCharacterStats(String variable) {
        super(CharacterStats.class, forVariable(variable));
    }

    public QCharacterStats(Path<? extends CharacterStats> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCharacterStats(PathMetadata metadata) {
        super(CharacterStats.class, metadata);
    }

}

