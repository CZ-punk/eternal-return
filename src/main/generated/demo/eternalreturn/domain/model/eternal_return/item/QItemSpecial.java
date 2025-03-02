package demo.eternalreturn.domain.model.eternal_return.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemSpecial is a Querydsl query type for ItemSpecial
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemSpecial extends EntityPathBase<ItemSpecial> {

    private static final long serialVersionUID = -725278268L;

    public static final QItemSpecial itemSpecial = new QItemSpecial("itemSpecial");

    public final BooleanPath alertInSpectator = createBoolean("alertInSpectator");

    public final BooleanPath autoDisappear = createBoolean("autoDisappear");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Integer> consumeCount = createNumber("consumeCount", Integer.class);

    public final NumberPath<Integer> cooldown = createNumber("cooldown", Integer.class);

    public final StringPath cooldownGroupType = createString("cooldownGroupType");

    public final NumberPath<Integer> creditValueWhenConvertedToBounty = createNumber("creditValueWhenConvertedToBounty", Integer.class);

    public final NumberPath<Integer> exclusiveProducer = createNumber("exclusiveProducer", Integer.class);

    public final NumberPath<Integer> ghostItemStateGroup = createNumber("ghostItemStateGroup", Integer.class);

    public final NumberPath<Integer> initialCount = createNumber("initialCount", Integer.class);

    public final BooleanPath isCanMonsterAreaItemDrop = createBoolean("isCanMonsterAreaItemDrop");

    public final BooleanPath isCompletedItem = createBoolean("isCompletedItem");

    public final BooleanPath isReduceLootOnDeath = createBoolean("isReduceLootOnDeath");

    public final BooleanPath isRemovedFromPlayerCorpseInventoryWhenPlayerKilled = createBoolean("isRemovedFromPlayerCorpseInventoryWhenPlayerKilled");

    public final StringPath itemGrade = createString("itemGrade");

    public final StringPath itemType = createString("itemType");

    public final StringPath itemUsableType = createString("itemUsableType");

    public final NumberPath<Integer> itemUsableValueList = createNumber("itemUsableValueList", Integer.class);

    public final NumberPath<Integer> makeMaterial1 = createNumber("makeMaterial1", Integer.class);

    public final NumberPath<Integer> makeMaterial2 = createNumber("makeMaterial2", Integer.class);

    public final NumberPath<Integer> manufacturableType = createNumber("manufacturableType", Integer.class);

    public final StringPath markingType = createString("markingType");

    public final NumberPath<Integer> modeType = createNumber("modeType", Integer.class);

    public final StringPath name = createString("name");

    public final BooleanPath restoreItemWhenResurrected = createBoolean("restoreItemWhenResurrected");

    public final BooleanPath showInItemBook = createBoolean("showInItemBook");

    public final StringPath specialItemType = createString("specialItemType");

    public final NumberPath<Integer> summonCode = createNumber("summonCode", Integer.class);

    public QItemSpecial(String variable) {
        super(ItemSpecial.class, forVariable(variable));
    }

    public QItemSpecial(Path<? extends ItemSpecial> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemSpecial(PathMetadata metadata) {
        super(ItemSpecial.class, metadata);
    }

}

