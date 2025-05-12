package demo.eternalreturn.domain.model.eternal_return.season;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSeason is a Querydsl query type for Season
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeason extends EntityPathBase<Season> {

    private static final long serialVersionUID = -1856569099L;

    public static final QSeason season = new QSeason("season");

    public final NumberPath<Integer> isCurrent = createNumber("isCurrent", Integer.class);

    public final StringPath seasonEnd = createString("seasonEnd");

    public final NumberPath<Integer> seasonID = createNumber("seasonID", Integer.class);

    public final StringPath seasonName = createString("seasonName");

    public final StringPath seasonStart = createString("seasonStart");

    public QSeason(String variable) {
        super(Season.class, forVariable(variable));
    }

    public QSeason(Path<? extends Season> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeason(PathMetadata metadata) {
        super(Season.class, metadata);
    }

}

