package demo.eternalreturn.domain.model.experiment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExperimentAttribute is a Querydsl query type for ExperimentAttribute
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExperimentAttribute extends EntityPathBase<ExperimentAttribute> {

    private static final long serialVersionUID = -1368269167L;

    public static final QExperimentAttribute experimentAttribute = new QExperimentAttribute("experimentAttribute");

    public final NumberPath<Integer> assistance = createNumber("assistance", Integer.class);

    public final NumberPath<Integer> attack = createNumber("attack", Integer.class);

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Integer> controlDifficulty = createNumber("controlDifficulty", Integer.class);

    public final NumberPath<Integer> defense = createNumber("defense", Integer.class);

    public final NumberPath<Integer> disruptor = createNumber("disruptor", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<demo.eternalreturn.domain.model.constant.WeaponType> mastery = createEnum("mastery", demo.eternalreturn.domain.model.constant.WeaponType.class);

    public final NumberPath<Integer> move = createNumber("move", Integer.class);

    public final StringPath name = createString("name");

    public QExperimentAttribute(String variable) {
        super(ExperimentAttribute.class, forVariable(variable));
    }

    public QExperimentAttribute(Path<? extends ExperimentAttribute> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExperimentAttribute(PathMetadata metadata) {
        super(ExperimentAttribute.class, metadata);
    }

}

