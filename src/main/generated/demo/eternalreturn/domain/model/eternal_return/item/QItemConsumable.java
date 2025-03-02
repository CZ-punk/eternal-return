package demo.eternalreturn.domain.model.eternal_return.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemConsumable is a Querydsl query type for ItemConsumable
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemConsumable extends EntityPathBase<ItemConsumable> {

    private static final long serialVersionUID = 2047270264L;

    public static final QItemConsumable itemConsumable = new QItemConsumable("itemConsumable");

    public final NumberPath<Double> addStateCode = createNumber("addStateCode", Double.class);

    public final NumberPath<Double> attackPowerByBuff = createNumber("attackPowerByBuff", Double.class);

    public final BooleanPath autoDisappear = createBoolean("autoDisappear");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final StringPath consumableType = createString("consumableType");

    public final NumberPath<Double> defenseByBuff = createNumber("defenseByBuff", Double.class);

    public final NumberPath<Double> heal = createNumber("heal", Double.class);

    public final NumberPath<Double> hpRecover = createNumber("hpRecover", Double.class);

    public final NumberPath<Integer> initialCount = createNumber("initialCount", Integer.class);

    public final BooleanPath isCanMonsterAreaItemDrop = createBoolean("isCanMonsterAreaItemDrop");

    public final BooleanPath isCompletedItem = createBoolean("isCompletedItem");

    public final NumberPath<Double> isReduceLootOnDeath = createNumber("isReduceLootOnDeath", Double.class);

    public final BooleanPath isRemovedFromPlayerCorpseInventoryWhenPlayerKilled = createBoolean("isRemovedFromPlayerCorpseInventoryWhenPlayerKilled");

    public final StringPath itemGrade = createString("itemGrade");

    public final StringPath itemType = createString("itemType");

    public final NumberPath<Integer> makeMaterial1 = createNumber("makeMaterial1", Integer.class);

    public final NumberPath<Integer> makeMaterial2 = createNumber("makeMaterial2", Integer.class);

    public final NumberPath<Integer> manufacturableType = createNumber("manufacturableType", Integer.class);

    public final StringPath name = createString("name");

    public final BooleanPath restoreItemWhenResurrected = createBoolean("restoreItemWhenResurrected");

    public final BooleanPath showInItemBook = createBoolean("showInItemBook");

    public final NumberPath<Double> skillAmpByBuff = createNumber("skillAmpByBuff", Double.class);

    public final NumberPath<Double> skillAmpRatioByBuff = createNumber("skillAmpRatioByBuff", Double.class);

    public final NumberPath<Double> spRecover = createNumber("spRecover", Double.class);

    public QItemConsumable(String variable) {
        super(ItemConsumable.class, forVariable(variable));
    }

    public QItemConsumable(Path<? extends ItemConsumable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemConsumable(PathMetadata metadata) {
        super(ItemConsumable.class, metadata);
    }

}

