package demo.eternalreturn.domain.model.eternal_return.experiment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExperimentExp is a Querydsl query type for ExperimentExp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExperimentExp extends EntityPathBase<ExperimentExp> {

    private static final long serialVersionUID = 1281110440L;

    public static final QExperimentExp experimentExp = new QExperimentExp("experimentExp");

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final NumberPath<Integer> levelUpExp = createNumber("levelUpExp", Integer.class);

    public QExperimentExp(String variable) {
        super(ExperimentExp.class, forVariable(variable));
    }

    public QExperimentExp(Path<? extends ExperimentExp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExperimentExp(PathMetadata metadata) {
        super(ExperimentExp.class, metadata);
    }

}

