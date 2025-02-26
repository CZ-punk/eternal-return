package demo.eternalreturn.domain.repository.item.custom;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.eternalreturn.infrastructure.converter.TupleConverter;
import demo.eternalreturn.presentation.dto.request.eternal_return.cond.ItemArmorCond;
import demo.eternalreturn.presentation.dto.request.eternal_return.cond.ItemConsumableCond;
import demo.eternalreturn.presentation.dto.request.eternal_return.cond.ItemWeaponCond;
import demo.eternalreturn.presentation.dto.response.eternal_return.item.ResItemArmorDto;
import demo.eternalreturn.presentation.dto.response.eternal_return.item.ResItemConsumableDto;
import demo.eternalreturn.presentation.dto.response.eternal_return.item.ResItemWeaponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static demo.eternalreturn.domain.model.eternal_return.item.QItemArmor.itemArmor;
import static demo.eternalreturn.domain.model.eternal_return.item.QItemConsumable.itemConsumable;
import static demo.eternalreturn.domain.model.eternal_return.item.QItemWeapon.itemWeapon;


@Repository
@RequiredArgsConstructor
public class ItemCustomRepositoryImpl implements ItemCustomRepository {

    @Autowired
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ResItemConsumableDto> searchItemConsumable(ItemConsumableCond cond) {
        List<Tuple> tupleList = queryFactory.select(
                        itemConsumable.code,
                        itemConsumable.name,
                        itemConsumable.itemType,
                        itemConsumable.itemGrade,
                        itemConsumable.isCompletedItem,
                        itemConsumable.makeMaterial1,
                        itemConsumable.makeMaterial2,
                        itemConsumable.hpRecover,
                        itemConsumable.spRecover
                ).from(itemConsumable)
                .where(
                        condEqCode(cond.getCode(), itemConsumable.code),
                        condLikeName(cond.getName(), itemConsumable.name),
                        condEqItemGrade(cond.getItemGrade(), itemConsumable.itemGrade)
                )
                .orderBy(
                        orderByItemGrade(cond.getItemGradeIsAsc(), itemConsumable.itemGrade)
                )
                .fetch();

        return TupleConverter.convertTuplesToDto(tupleList, ResItemConsumableDto.class);
    }

    // TODO: 추후 Item Stat Table 나눌 지 고민
    @Override
    public List<ResItemArmorDto> searchItemArmor(ItemArmorCond cond) {
        List<Tuple> tupleList = queryFactory.select(
                        itemArmor.code,
                        itemArmor.name,
                        itemArmor.itemType,
                        itemArmor.armorType,
                        itemArmor.itemGrade,
                        itemArmor.isCompletedItem,
                        itemArmor.makeMaterial1,
                        itemArmor.makeMaterial2,
                        itemArmor.upgradeItemCode,
                        itemArmor.attackPower,
                        itemArmor.attackPowerByLv,
                        itemArmor.defense,
                        itemArmor.defenseByLv,
                        itemArmor.skillAmp,
                        itemArmor.skillAmpByLevel,
                        itemArmor.adaptiveForce,
                        itemArmor.maxHp,
                        itemArmor.maxHpByLv,
                        itemArmor.maxSp,
                        itemArmor.hpRegenRatio,
                        itemArmor.spRegenRatio,
                        itemArmor.attackSpeedRatio,
                        itemArmor.criticalStrikeChance,
                        itemArmor.criticalStrikeDamage,
                        itemArmor.cooldownReduction,
                        itemArmor.lifeSteal,
                        itemArmor.normalLifeSteal,
                        itemArmor.moveSpeed,
                        itemArmor.moveSpeedRatio,
                        itemArmor.sightRange,
                        itemArmor.preventSkillDamagedRatio,
                        itemArmor.penetrationDefense,
                        itemArmor.penetrationDefenseRatio,
                        itemArmor.slowResistRatio,
                        itemArmor.healerGiveHpHealRatio,
                        itemArmor.uniqueAttackRange,
                        itemArmor.uniqueCooldownLimit,
                        itemArmor.uniqueTenacity,
                        itemArmor.uniqueSkillAmpRatio
                )
                .from(itemArmor)
                .where(
                        condEqCode(cond.getCode(), itemArmor.code),
                        condLikeName(cond.getName(), itemArmor.name),
                        condEqArmorType(cond.getArmorType()),
                        condEqItemGrade(cond.getItemGrade(), itemArmor.itemGrade)
                )
                .orderBy(orderByItemGrade(cond.getItemGradeIsAsc(), itemArmor.itemGrade))
                .fetch();

        return TupleConverter.convertTuplesToDto(tupleList, ResItemArmorDto.class);
    }

    @Override
    public List<ResItemWeaponDto> searchItemWeapon(ItemWeaponCond cond) {
        List<Tuple> tupleList = queryFactory.select(
                        itemWeapon.code,
                        itemWeapon.name,
                        itemWeapon.itemType,
                        itemWeapon.weaponType,
                        itemWeapon.itemGrade,
                        itemWeapon.isCompletedItem,
                        itemWeapon.makeMaterial1,
                        itemWeapon.makeMaterial2,
                        itemWeapon.attackPower,
                        itemWeapon.attackPowerByLv,
                        itemWeapon.defense,
                        itemWeapon.defenseByLv,
                        itemWeapon.skillAmp,
                        itemWeapon.skillAmpByLevel,
                        itemWeapon.adaptiveForce,
                        itemWeapon.maxHp,
                        itemWeapon.maxHpByLv,
                        itemWeapon.maxSP,                           // EternalReturn API Field...
                        itemWeapon.hpRegenRatio,
                        itemWeapon.spRegenRatio,
                        itemWeapon.attackSpeedRatio,
                        itemWeapon.criticalStrikeChance,
                        itemWeapon.criticalStrikeDamage,
                        itemWeapon.cooldownReduction,
                        itemWeapon.lifeSteal,
                        itemWeapon.normalLifeSteal,
                        itemWeapon.moveSpeed,
                        itemWeapon.moveSpeedRatio,
                        itemWeapon.sightRange,
                        itemWeapon.preventSkillDamagedRatio,
                        itemWeapon.penetrationDefense,
                        itemWeapon.penetrationDefenseRatio,
                        itemWeapon.slowResistRatio,
                        itemWeapon.healerGiveHpHealRatio,
                        itemWeapon.uniqueAttackRange,
                        itemWeapon.uniqueCooldownLimit,
                        itemWeapon.uniqueTenacity,
                        itemWeapon.uniqueSkillAmpRatio
                )
                .from(itemWeapon)
                .where(
                        condEqCode(cond.getCode(), itemWeapon.code),
                        condLikeName(cond.getName(), itemWeapon.name),
                        condEqWeaponType(cond.getWeaponType()),
                        condEqItemGrade(cond.getItemGrade(), itemWeapon.itemGrade)
                )
                .orderBy(orderByItemGrade(cond.getItemGradeIsAsc(), itemWeapon.itemGrade))
                .fetch();

        return TupleConverter.convertTuplesToDto(tupleList, ResItemWeaponDto.class);
    }

    private BooleanExpression condEqWeaponType(String weaponType) {
        return weaponType != null && !weaponType.isBlank() ? itemWeapon.weaponType.eq(weaponType) : null;
    }

    private BooleanExpression condEqCode(Integer code, NumberPath<Integer> pathName) {
        return code != null ? pathName.eq(code) : null;
    }

    private BooleanExpression condEqArmorType(String armorType) {
        return armorType != null && !armorType.isBlank() ? itemArmor.armorType.eq(armorType) : null;
    }

    private BooleanExpression condLikeName(String name, StringPath pathName) {
        return name != null && !name.isBlank() ? pathName.like("%" + name + "%") : null;
    }

    private BooleanExpression condEqItemGrade(String itemGrade, StringPath pathName) {
        return itemGrade != null && !itemGrade.isBlank() ? pathName.eq(itemGrade) : null;
    }

    private OrderSpecifier<?>[] orderByItemGrade(Boolean itemGradeIsAsc, StringPath itemGrade) {
        NumberExpression<Integer> gradeValue = Expressions.numberTemplate(Integer.class,
                "case when {0} = 'Common' then 1 " +
                        "when {0} = 'Uncommon' then 2 " +
                        "when {0} = 'Rare' then 3 " +
                        "when {0} = 'Epic' then 4 " +
                        "when {0} = 'Legend' then 5 " +
                        "else 6 end", itemGrade);

        if (itemGradeIsAsc == null) return new OrderSpecifier<?>[]{gradeValue.asc()};
        OrderSpecifier<?> orderSpecifier = itemGradeIsAsc ? gradeValue.asc() : gradeValue.desc();
        return new OrderSpecifier<?>[]{orderSpecifier};
    }


}
