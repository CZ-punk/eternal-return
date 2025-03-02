package demo.eternalreturn.domain.model.eternal_return.experiment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExperimentSkin is a Querydsl query type for ExperimentSkin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExperimentSkin extends EntityPathBase<ExperimentSkin> {

    private static final long serialVersionUID = 1060122450L;

    public static final QExperimentSkin experimentSkin = new QExperimentSkin("experimentSkin");

    public final NumberPath<Integer> _index = createNumber("_index", Integer.class);

    public final NumberPath<Integer> characterCode = createNumber("characterCode", Integer.class);

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final StringPath effectsPath = createString("effectsPath");

    public final BooleanPath eventFree = createBoolean("eventFree");

    public final StringPath fxSoundPath = createString("fxSoundPath");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final StringPath indicatorPath = createString("indicatorPath");

    public final StringPath name = createString("name");

    public final StringPath objectPath = createString("objectPath");

    public final StringPath projectilesPath = createString("projectilesPath");

    public final StringPath purchaseType = createString("purchaseType");

    public final StringPath voicePath = createString("voicePath");

    public final StringPath weaponMountCommonPath = createString("weaponMountCommonPath");

    public final StringPath weaponMountPath = createString("weaponMountPath");

    public QExperimentSkin(String variable) {
        super(ExperimentSkin.class, forVariable(variable));
    }

    public QExperimentSkin(Path<? extends ExperimentSkin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExperimentSkin(PathMetadata metadata) {
        super(ExperimentSkin.class, metadata);
    }

}

