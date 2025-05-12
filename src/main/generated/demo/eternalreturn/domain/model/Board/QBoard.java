package demo.eternalreturn.domain.model.Board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 2104537979L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final demo.eternalreturn.domain.model.QBaseEntity _super = new demo.eternalreturn.domain.model.QBaseEntity(this);

    public final StringPath author = createString("author");

    public final NumberPath<Integer> commentCount = createNumber("commentCount", Integer.class);

    public final ListPath<demo.eternalreturn.domain.model.comment.Comment, demo.eternalreturn.domain.model.comment.QComment> commentList = this.<demo.eternalreturn.domain.model.comment.Comment, demo.eternalreturn.domain.model.comment.QComment>createList("commentList", demo.eternalreturn.domain.model.comment.Comment.class, demo.eternalreturn.domain.model.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<String, StringPath> imageUrlList = this.<String, StringPath>createList("imageUrlList", String.class, StringPath.class, PathInits.DIRECT2);

    //inherited
    public final BooleanPath isDelete = _super.isDelete;

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final demo.eternalreturn.domain.model.Member.QMember member;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new demo.eternalreturn.domain.model.Member.QMember(forProperty("member")) : null;
    }

}

