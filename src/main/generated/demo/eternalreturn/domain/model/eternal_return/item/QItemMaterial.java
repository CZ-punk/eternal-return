package demo.eternalreturn.domain.model.eternal_return.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemMaterial is a Querydsl query type for ItemMaterial
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemMaterial extends EntityPathBase<ItemMaterial> {

    private static final long serialVersionUID = 1423156604L;

    public static final QItemMaterial itemMaterial = new QItemMaterial("itemMaterial");

    public final BooleanPath autoDisappear = createBoolean("autoDisappear");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Integer> initialCount = createNumber("initialCount", Integer.class);

    public final BooleanPath isCanMonsterAreaItemDrop = createBoolean("isCanMonsterAreaItemDrop");

    public final BooleanPath isRemovedFromPlayerCorpseInventoryWhenPlayerKilled = createBoolean("isRemovedFromPlayerCorpseInventoryWhenPlayerKilled");

    public final StringPath itemGrade = createString("itemGrade");

    public final NumberPath<Integer> makeMaterial1 = createNumber("makeMaterial1", Integer.class);

    public final NumberPath<Integer> makeMaterial2 = createNumber("makeMaterial2", Integer.class);

    public final NumberPath<Integer> manufacturableType = createNumber("manufacturableType", Integer.class);

    public final StringPath markingType = createString("markingType");

    public final StringPath name = createString("name");

    public final BooleanPath restoreItemWhenResurrected = createBoolean("restoreItemWhenResurrected");

    public final BooleanPath showInItemBook = createBoolean("showInItemBook");

    public QItemMaterial(String variable) {
        super(ItemMaterial.class, forVariable(variable));
    }

    public QItemMaterial(Path<? extends ItemMaterial> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemMaterial(PathMetadata metadata) {
        super(ItemMaterial.class, metadata);
    }

}

