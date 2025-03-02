package demo.eternalreturn.domain.model.eternal_return.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemSearchOptionV2 is a Querydsl query type for ItemSearchOptionV2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemSearchOptionV2 extends EntityPathBase<ItemSearchOptionV2> {

    private static final long serialVersionUID = -1642801106L;

    public static final QItemSearchOptionV2 itemSearchOptionV2 = new QItemSearchOptionV2("itemSearchOptionV2");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath tag1 = createString("tag1");

    public final StringPath tag2 = createString("tag2");

    public final StringPath tag3 = createString("tag3");

    public QItemSearchOptionV2(String variable) {
        super(ItemSearchOptionV2.class, forVariable(variable));
    }

    public QItemSearchOptionV2(Path<? extends ItemSearchOptionV2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemSearchOptionV2(PathMetadata metadata) {
        super(ItemSearchOptionV2.class, metadata);
    }

}

