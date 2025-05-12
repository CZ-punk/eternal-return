package demo.eternalreturn.domain.model.Member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1563226891L;

    public static final QMember member = new QMember("member1");

    public final demo.eternalreturn.domain.model.QBaseEntity _super = new demo.eternalreturn.domain.model.QBaseEntity(this);

    public final ListPath<demo.eternalreturn.domain.model.Board.Board, demo.eternalreturn.domain.model.Board.QBoard> boardList = this.<demo.eternalreturn.domain.model.Board.Board, demo.eternalreturn.domain.model.Board.QBoard>createList("boardList", demo.eternalreturn.domain.model.Board.Board.class, demo.eternalreturn.domain.model.Board.QBoard.class, PathInits.DIRECT2);

    public final ListPath<demo.eternalreturn.domain.model.comment.Comment, demo.eternalreturn.domain.model.comment.QComment> commentList = this.<demo.eternalreturn.domain.model.comment.Comment, demo.eternalreturn.domain.model.comment.QComment>createList("commentList", demo.eternalreturn.domain.model.comment.Comment.class, demo.eternalreturn.domain.model.comment.QComment.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final StringPath deletedBy = _super.deletedBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDelete = _super.isDelete;

    public final StringPath loginId = createString("loginId");

    public final StringPath loginPw = createString("loginPw");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final EnumPath<demo.eternalreturn.domain.constant.SocialProvider> provider = createEnum("provider", demo.eternalreturn.domain.constant.SocialProvider.class);

    public final StringPath providerEmail = createString("providerEmail");

    public final StringPath providerId = createString("providerId");

    public final StringPath refreshToken = createString("refreshToken");

    public final SetPath<MemberRole, QMemberRole> roles = this.<MemberRole, QMemberRole>createSet("roles", MemberRole.class, QMemberRole.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

