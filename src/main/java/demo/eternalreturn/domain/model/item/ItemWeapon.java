package demo.eternalreturn.domain.model.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_weapon")
public class ItemWeapon {

    @Id
    private Integer code;
    private String name;
    private Integer modeType;
    private String itemType;
    private String weaponType;
    private String itemGrade;
    private String gradeBgOverride;
    private Boolean isCompletedItem;
    private Boolean alertInSpectator;
    private String markingType;

    private Integer initialCount;
    private String itemUsableType;

    private Boolean isRemovedFromPlayerCorpseInventoryWhenPlayerKilled;
    private Integer makeMaterial1;
    private Integer makeMaterial2;

    private Boolean notDisarm;  // 탈부착 여부
    private Boolean consumable; // 소모품 여부 (무기 전부 false)
    private Boolean isCanMonsterAreaItemDrop;
    private Integer manufacturableType;

    private Double attackPower;
    private Double attackPowerByLv;
    private Double defense;
    private Double defenseByLv;
    private Double skillAmp;
    private Double skillAmpByLevel;
    private Double skillAmpRatio;
    private Double skillAmpRatioByLevel;
    private Double adaptiveForce;
    private Double adaptiveForceByLevel;
    private Double maxHp;
    private Double maxHpByLv;
    private Double hpRegenRatio;
    private Double hpRegen;
    @Column(name = "max_sp")
    private Double maxSP;
    private Double spRegenRatio;
    private Double spRegen;
    private Double attackSpeedRatio;
    private Double attackSpeedRatioByLv;
    private Double criticalStrikeChance;
    private Double criticalStrikeDamage;
    private Double cooldownReduction;
    private Double preventCriticalStrikeDamaged;
    private Double cooldownLimit;
    private Double lifeSteal;
    private Double normalLifeSteal;
    private Double skillLifeSteal;
    private Double moveSpeed;
    private Double moveSpeedRatio;
    private Double moveSpeedOutOfCombat;
    private Double sightRange;
    private Double attackRange;
    private Double increaseBasicAttackDamage;
    private Double increaseBasicAttackDamageByLv;
    private Double increaseBasicAttackDamageRatio;
    private Double increaseBasicAttackDamageRatioByLv;      // 레벨 당 기본 공격 증폭 %
    private Double preventBasicAttackDamaged;
    private Double preventBasicAttackDamagedByLv;
    private Double preventBasicAttackDamagedRatio;
    private Double preventBasicAttackDamagedRatioByLv;
    private Double preventSkillDamaged;
    private Double preventSkillDamagedByLv;
    private Double preventSkillDamagedRatio;
    private Double preventSkillDamagedRatioByLv;
    private Double penetrationDefense;
    private Double penetrationDefenseRatio;
    private Double trapDamageReduce;
    private Double trapDamageReduceRatio;
    private Double slowResistRatio;
    private Double hpHealedIncreaseRatio;
    private Double healerGiveHpHealRatio;
    private Double uniqueAttackRange;
    private Double uniqueHpHealedIncreaseRatio;
    private Double uniqueCooldownLimit;
    private Double uniqueTenacity;
    private Double uniqueMoveSpeed;
    private Double uniquePenetrationDefense;
    private Double uniquePenetrationDefenseRatio;
    private Double uniqueLifeSteal;
    private Double uniqueSkillAmpRatio;
    private Boolean restoreItemWhenResurrected;
    private Integer creditValueWhenConvertedToBounty;
    private Boolean autoDisappear;
    private Boolean showInItemBook;
}
