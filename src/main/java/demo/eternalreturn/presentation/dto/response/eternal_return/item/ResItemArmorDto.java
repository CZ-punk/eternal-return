package demo.eternalreturn.presentation.dto.response.eternal_return.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResItemArmorDto {

    private Integer code;
    private String name;
    private String itemType;
    private String armorType;
    private String itemGrade;
    private Boolean isCompletedItem;
    private Integer makeMaterial1;
    private Integer makeMaterial2;

    // 마이 업그레이드 아이템
    private Integer upgradeItemCode;

    private Double attackPower;
    private Double attackPowerByLv;
    private Double defense;
    private Double defenseByLv;
    private Double skillAmp;
    private Double skillAmpByLevel;

    // Item 고유 능력치 ( 스증 == 공 * 2 )
    private Double adaptiveForce;

    private Double maxHp;
    private Double maxHpByLv;
    private Double maxSp;

    private Double hpRegenRatio;
    private Double spRegenRatio;

    private Double attackSpeedRatio;
    private Double criticalStrikeChance;
    private Double criticalStrikeDamage;

    private Double cooldownReduction;
    private Double lifeSteal;
    private Double normalLifeSteal;
    
    private Double moveSpeed;                // 이속 깡증
    private Double moveSpeedRatio;           // 이속 퍼증
    private Double sightRange;

    private Double preventSkillDamagedRatio; // 스킬 피해 감소 %

    private Double penetrationDefense;       // 방어 관통 깡
    private Double penetrationDefenseRatio;  // 방어 관통 퍼
    
    private Double slowResistRatio;          // 슬로우 저항
    private Double healerGiveHpHealRatio;    // 주는 회복 효과

    // Unique Prefix Item ==> "고유"스텟
    private Double uniqueAttackRange;        // 공격 범위
    private Double uniqueCooldownLimit;      // 최대 쿨감 %
    private Double uniqueTenacity;           // 방해 효과 저항 %
    private Double uniqueSkillAmpRatio;      // 스킬 증폭

}
