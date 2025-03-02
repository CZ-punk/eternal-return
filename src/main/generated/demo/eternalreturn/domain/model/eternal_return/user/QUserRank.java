package demo.eternalreturn.domain.model.eternal_return.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRank is a Querydsl query type for UserRank
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserRank extends EntityPathBase<UserRank> {

    private static final long serialVersionUID = -797625855L;

    public static final QUserRank userRank = new QUserRank("userRank");

    public final NumberPath<Integer> mmr = createNumber("mmr", Integer.class);

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    public final NumberPath<Integer> serverCode = createNumber("serverCode", Integer.class);

    public final NumberPath<Integer> serverRank = createNumber("serverRank", Integer.class);

    public final NumberPath<Integer> userNum = createNumber("userNum", Integer.class);

    public QUserRank(String variable) {
        super(UserRank.class, forVariable(variable));
    }

    public QUserRank(Path<? extends UserRank> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRank(PathMetadata metadata) {
        super(UserRank.class, metadata);
    }

}

