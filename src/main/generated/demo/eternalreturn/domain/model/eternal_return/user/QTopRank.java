package demo.eternalreturn.domain.model.eternal_return.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTopRank is a Querydsl query type for TopRank
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTopRank extends EntityPathBase<TopRank> {

    private static final long serialVersionUID = -1295624585L;

    public static final QTopRank topRank = new QTopRank("topRank");

    public final NumberPath<Integer> mmr = createNumber("mmr", Integer.class);

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    public final NumberPath<Integer> userNum = createNumber("userNum", Integer.class);

    public QTopRank(String variable) {
        super(TopRank.class, forVariable(variable));
    }

    public QTopRank(Path<? extends TopRank> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTopRank(PathMetadata metadata) {
        super(TopRank.class, metadata);
    }

}

