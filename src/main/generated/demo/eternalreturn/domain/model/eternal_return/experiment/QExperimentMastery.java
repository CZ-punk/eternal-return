package demo.eternalreturn.domain.model.eternal_return.experiment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExperimentMastery is a Querydsl query type for ExperimentMastery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExperimentMastery extends EntityPathBase<ExperimentMastery> {

    private static final long serialVersionUID = -93451166L;

    public static final QExperimentMastery experimentMastery = new QExperimentMastery("experimentMastery");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final StringPath combat1 = createString("combat1");

    public final StringPath combat2 = createString("combat2");

    public final StringPath survival1 = createString("survival1");

    public final StringPath survival2 = createString("survival2");

    public final StringPath survival3 = createString("survival3");

    public final StringPath weapon1 = createString("weapon1");

    public final StringPath weapon2 = createString("weapon2");

    public final StringPath weapon3 = createString("weapon3");

    public final StringPath weapon4 = createString("weapon4");

    public QExperimentMastery(String variable) {
        super(ExperimentMastery.class, forVariable(variable));
    }

    public QExperimentMastery(Path<? extends ExperimentMastery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExperimentMastery(PathMetadata metadata) {
        super(ExperimentMastery.class, metadata);
    }

}

