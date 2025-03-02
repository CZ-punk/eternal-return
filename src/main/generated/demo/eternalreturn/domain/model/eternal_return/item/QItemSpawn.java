package demo.eternalreturn.domain.model.eternal_return.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemSpawn is a Querydsl query type for ItemSpawn
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemSpawn extends EntityPathBase<ItemSpawn> {

    private static final long serialVersionUID = 437230406L;

    public static final QItemSpawn itemSpawn = new QItemSpawn("itemSpawn");

    public final NumberPath<Integer> areaCode = createNumber("areaCode", Integer.class);

    public final NumberPath<Integer> areaSpawnGroup = createNumber("areaSpawnGroup", Integer.class);

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Integer> dropCount = createNumber("dropCount", Integer.class);

    public final NumberPath<Integer> itemCode = createNumber("itemCode", Integer.class);

    public QItemSpawn(String variable) {
        super(ItemSpawn.class, forVariable(variable));
    }

    public QItemSpawn(Path<? extends ItemSpawn> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemSpawn(PathMetadata metadata) {
        super(ItemSpawn.class, metadata);
    }

}

