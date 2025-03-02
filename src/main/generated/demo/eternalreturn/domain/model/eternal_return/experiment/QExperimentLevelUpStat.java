package demo.eternalreturn.domain.model.eternal_return.experiment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExperimentLevelUpStat is a Querydsl query type for ExperimentLevelUpStat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExperimentLevelUpStat extends EntityPathBase<ExperimentLevelUpStat> {

    private static final long serialVersionUID = -448691010L;

    public static final QExperimentLevelUpStat experimentLevelUpStat = new QExperimentLevelUpStat("experimentLevelUpStat");

    public final NumberPath<Double> attackPower = createNumber("attackPower", Double.class);

    public final NumberPath<Double> attackSpeed = createNumber("attackSpeed", Double.class);

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Double> criticalChance = createNumber("criticalChance", Double.class);

    public final NumberPath<Double> defense = createNumber("defense", Double.class);

    public final NumberPath<Double> hpRegen = createNumber("hpRegen", Double.class);

    public final NumberPath<Integer> maxHp = createNumber("maxHp", Integer.class);

    public final NumberPath<Integer> maxSp = createNumber("maxSp", Integer.class);

    public final NumberPath<Double> moveSpeed = createNumber("moveSpeed", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> spRegen = createNumber("spRegen", Double.class);

    public QExperimentLevelUpStat(String variable) {
        super(ExperimentLevelUpStat.class, forVariable(variable));
    }

    public QExperimentLevelUpStat(Path<? extends ExperimentLevelUpStat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExperimentLevelUpStat(PathMetadata metadata) {
        super(ExperimentLevelUpStat.class, metadata);
    }

}

