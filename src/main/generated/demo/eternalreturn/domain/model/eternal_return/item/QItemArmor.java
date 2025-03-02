package demo.eternalreturn.domain.model.eternal_return.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemArmor is a Querydsl query type for ItemArmor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemArmor extends EntityPathBase<ItemArmor> {

    private static final long serialVersionUID = 420677898L;

    public static final QItemArmor itemArmor = new QItemArmor("itemArmor");

    public final NumberPath<Double> adaptiveForce = createNumber("adaptiveForce", Double.class);

    public final NumberPath<Double> adaptiveForceByLevel = createNumber("adaptiveForceByLevel", Double.class);

    public final StringPath armorType = createString("armorType");

    public final NumberPath<Double> attackPower = createNumber("attackPower", Double.class);

    public final NumberPath<Double> attackPowerByLv = createNumber("attackPowerByLv", Double.class);

    public final NumberPath<Double> attackRange = createNumber("attackRange", Double.class);

    public final NumberPath<Double> attackSpeedRatio = createNumber("attackSpeedRatio", Double.class);

    public final NumberPath<Double> attackSpeedRatioByLv = createNumber("attackSpeedRatioByLv", Double.class);

    public final BooleanPath autoDisappear = createBoolean("autoDisappear");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final NumberPath<Double> cooldownLimit = createNumber("cooldownLimit", Double.class);

    public final NumberPath<Double> cooldownReduction = createNumber("cooldownReduction", Double.class);

    public final NumberPath<Double> criticalStrikeChance = createNumber("criticalStrikeChance", Double.class);

    public final NumberPath<Double> criticalStrikeDamage = createNumber("criticalStrikeDamage", Double.class);

    public final NumberPath<Double> defense = createNumber("defense", Double.class);

    public final NumberPath<Double> defenseByLv = createNumber("defenseByLv", Double.class);

    public final NumberPath<Double> healerGiveHpHealRatio = createNumber("healerGiveHpHealRatio", Double.class);

    public final NumberPath<Double> hpHealedIncreaseRatio = createNumber("hpHealedIncreaseRatio", Double.class);

    public final NumberPath<Double> hpRegen = createNumber("hpRegen", Double.class);

    public final NumberPath<Double> hpRegenRatio = createNumber("hpRegenRatio", Double.class);

    public final NumberPath<Double> increaseBasicAttackDamage = createNumber("increaseBasicAttackDamage", Double.class);

    public final NumberPath<Double> increaseBasicAttackDamageByLv = createNumber("increaseBasicAttackDamageByLv", Double.class);

    public final NumberPath<Double> increaseBasicAttackDamageRatio = createNumber("increaseBasicAttackDamageRatio", Double.class);

    public final NumberPath<Double> increaseBasicAttackDamageRatioByLv = createNumber("increaseBasicAttackDamageRatioByLv", Double.class);

    public final NumberPath<Integer> initialCount = createNumber("initialCount", Integer.class);

    public final BooleanPath isCanMonsterAreaItemDrop = createBoolean("isCanMonsterAreaItemDrop");

    public final BooleanPath isCompletedItem = createBoolean("isCompletedItem");

    public final BooleanPath isRemovedFromPlayerCorpseInventoryWhenPlayerKilled = createBoolean("isRemovedFromPlayerCorpseInventoryWhenPlayerKilled");

    public final StringPath itemGrade = createString("itemGrade");

    public final StringPath itemType = createString("itemType");

    public final StringPath itemUsableType = createString("itemUsableType");

    public final NumberPath<Double> lifeSteal = createNumber("lifeSteal", Double.class);

    public final NumberPath<Integer> makeMaterial1 = createNumber("makeMaterial1", Integer.class);

    public final NumberPath<Integer> makeMaterial2 = createNumber("makeMaterial2", Integer.class);

    public final NumberPath<Integer> manufacturableType = createNumber("manufacturableType", Integer.class);

    public final StringPath markingType = createString("markingType");

    public final NumberPath<Double> maxHp = createNumber("maxHp", Double.class);

    public final NumberPath<Double> maxHpByLv = createNumber("maxHpByLv", Double.class);

    public final NumberPath<Double> maxSp = createNumber("maxSp", Double.class);

    public final NumberPath<Double> moveSpeed = createNumber("moveSpeed", Double.class);

    public final NumberPath<Double> moveSpeedOutOfCombat = createNumber("moveSpeedOutOfCombat", Double.class);

    public final NumberPath<Double> moveSpeedRatio = createNumber("moveSpeedRatio", Double.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> normalLifeSteal = createNumber("normalLifeSteal", Double.class);

    public final NumberPath<Double> penetrationDefense = createNumber("penetrationDefense", Double.class);

    public final NumberPath<Double> penetrationDefenseRatio = createNumber("penetrationDefenseRatio", Double.class);

    public final NumberPath<Double> preventBasicAttackDamaged = createNumber("preventBasicAttackDamaged", Double.class);

    public final NumberPath<Double> preventBasicAttackDamagedByLv = createNumber("preventBasicAttackDamagedByLv", Double.class);

    public final NumberPath<Double> preventBasicAttackDamagedRatio = createNumber("preventBasicAttackDamagedRatio", Double.class);

    public final NumberPath<Double> preventBasicAttackDamagedRatioByLv = createNumber("preventBasicAttackDamagedRatioByLv", Double.class);

    public final NumberPath<Double> preventCriticalStrikeDamaged = createNumber("preventCriticalStrikeDamaged", Double.class);

    public final NumberPath<Double> preventSkillDamaged = createNumber("preventSkillDamaged", Double.class);

    public final NumberPath<Double> preventSkillDamagedByLv = createNumber("preventSkillDamagedByLv", Double.class);

    public final NumberPath<Double> preventSkillDamagedRatio = createNumber("preventSkillDamagedRatio", Double.class);

    public final NumberPath<Double> preventSkillDamagedRatioByLv = createNumber("preventSkillDamagedRatioByLv", Double.class);

    public final BooleanPath restoreItemWhenResurrected = createBoolean("restoreItemWhenResurrected");

    public final BooleanPath showInItemBook = createBoolean("showInItemBook");

    public final NumberPath<Double> sightRange = createNumber("sightRange", Double.class);

    public final NumberPath<Double> skillAmp = createNumber("skillAmp", Double.class);

    public final NumberPath<Double> skillAmpByLevel = createNumber("skillAmpByLevel", Double.class);

    public final NumberPath<Double> skillAmpRatio = createNumber("skillAmpRatio", Double.class);

    public final NumberPath<Double> skillAmpRatioByLevel = createNumber("skillAmpRatioByLevel", Double.class);

    public final NumberPath<Double> skillLifeSteal = createNumber("skillLifeSteal", Double.class);

    public final NumberPath<Double> slowResistRatio = createNumber("slowResistRatio", Double.class);

    public final NumberPath<Double> spRegen = createNumber("spRegen", Double.class);

    public final NumberPath<Double> spRegenRatio = createNumber("spRegenRatio", Double.class);

    public final NumberPath<Double> trapDamageReduce = createNumber("trapDamageReduce", Double.class);

    public final NumberPath<Double> trapDamageReduceRatio = createNumber("trapDamageReduceRatio", Double.class);

    public final NumberPath<Double> uniqueAttackRange = createNumber("uniqueAttackRange", Double.class);

    public final NumberPath<Double> uniqueCooldownLimit = createNumber("uniqueCooldownLimit", Double.class);

    public final NumberPath<Double> uniqueHpHealedIncreaseRatio = createNumber("uniqueHpHealedIncreaseRatio", Double.class);

    public final NumberPath<Double> uniqueLifeSteal = createNumber("uniqueLifeSteal", Double.class);

    public final NumberPath<Double> uniqueMoveSpeed = createNumber("uniqueMoveSpeed", Double.class);

    public final NumberPath<Double> uniquePenetrationDefense = createNumber("uniquePenetrationDefense", Double.class);

    public final NumberPath<Double> uniquePenetrationDefenseRatio = createNumber("uniquePenetrationDefenseRatio", Double.class);

    public final NumberPath<Double> uniqueSkillAmpRatio = createNumber("uniqueSkillAmpRatio", Double.class);

    public final NumberPath<Double> uniqueTenacity = createNumber("uniqueTenacity", Double.class);

    public final NumberPath<Integer> upgradeItemCode = createNumber("upgradeItemCode", Integer.class);

    public QItemArmor(String variable) {
        super(ItemArmor.class, forVariable(variable));
    }

    public QItemArmor(Path<? extends ItemArmor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemArmor(PathMetadata metadata) {
        super(ItemArmor.class, metadata);
    }

}

