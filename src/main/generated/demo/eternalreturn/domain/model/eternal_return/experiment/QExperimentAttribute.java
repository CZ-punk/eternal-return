package demo.eternalreturn.domain.model.eternal_return.experiment;

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

    private static final long serialVersionUID = 189480423L;

    public static final QExperimentAttribute experimentAttribute = new QExperimentAttribute("experimentAttribute");

    public final NumberPath<Integer> assistance = createNumber("assistance", Integer.class);

    public final NumberPath<Integer> attack = createNumber("attack", Integer.class);

    public final StringPath character = createString("character");

    public final NumberPath<Integer> characterCode = createNumber("characterCode", Integer.class);

    public final NumberPath<Integer> controlDifficulty = createNumber("controlDifficulty", Integer.class);

    public final NumberPath<Integer> defense = createNumber("defense", Integer.class);

    public final NumberPath<Integer> disruptor = createNumber("disruptor", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final EnumPath<demo.eternalreturn.domain.constant.WeaponType> mastery = createEnum("mastery", demo.eternalreturn.domain.constant.WeaponType.class);

    public final NumberPath<Integer> move = createNumber("move", Integer.class);

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

