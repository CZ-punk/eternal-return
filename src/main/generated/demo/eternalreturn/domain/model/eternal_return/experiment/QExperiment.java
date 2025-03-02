package demo.eternalreturn.domain.model.eternal_return.experiment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExperiment is a Querydsl query type for Experiment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExperiment extends EntityPathBase<Experiment> {

    private static final long serialVersionUID = 702150709L;

    public static final QExperiment experiment = new QExperiment("experiment");

    public final NumberPath<Integer> attackPower = createNumber("attackPower", Integer.class);

    public final NumberPath<Double> attackSpeed = createNumber("attackSpeed", Double.class);

    public final NumberPath<Double> attackSpeedLimit = createNumber("attackSpeedLimit", Double.class);

    public final NumberPath<Double> attackSpeedMin = createNumber("attackSpeedMin", Double.class);

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Integer> defense = createNumber("defense", Integer.class);

    public final NumberPath<Double> hpRegen = createNumber("hpRegen", Double.class);

    public final NumberPath<Integer> initExtraPoint = createNumber("initExtraPoint", Integer.class);

    public final NumberPath<Integer> maxExtraPoint = createNumber("maxExtraPoint", Integer.class);

    public final NumberPath<Integer> maxHp = createNumber("maxHp", Integer.class);

    public final NumberPath<Integer> maxSp = createNumber("maxSp", Integer.class);

    public final NumberPath<Double> moveSpeed = createNumber("moveSpeed", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> sightRange = createNumber("sightRange", Double.class);

    public final NumberPath<Integer> skillAmp = createNumber("skillAmp", Integer.class);

    public final NumberPath<Double> spRegen = createNumber("spRegen", Double.class);

    public final StringPath strLearnStartSkill = createString("strLearnStartSkill");

    public final StringPath strUsePointLearnStartSkill = createString("strUsePointLearnStartSkill");

    public QExperiment(String variable) {
        super(Experiment.class, forVariable(variable));
    }

    public QExperiment(Path<? extends Experiment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExperiment(PathMetadata metadata) {
        super(Experiment.class, metadata);
    }

}

