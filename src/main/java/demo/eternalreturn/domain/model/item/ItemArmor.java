package demo.eternalreturn.domain.model.item;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "item_armor")
public class ItemArmor {

    @Id
    private Integer code;
    private String name;
    private String itemType;
    private String armorType;
    private String itemGrade;
    private Boolean isCompletedItem;            // 완제템 유무
    private String markingType;                 // 업그레이드 템 유무 (ex. 마이 업그레이드 )
    private Integer initialCount;               // 드랍 시, 갯수
    private String itemUsableType;
    private Boolean isRemovedFromPlayerCorpseInventoryWhenPlayerKilled; // 플레이어 사망 시, 시체 인벤토리 템 유지 유무
    private Integer upgradeItemCode;            // 0: 업그레이드 불가, not 0: 업그레이드 가능한 ItemCode
    private Integer makeMaterial1;              // 재료1
    private Integer makeMaterial2;              // 재료2

    private Boolean isCanMonsterAreaItemDrop;   // 몬스터 드랍 가능 유무
    private Integer manufacturableType;         // 0: 제조템 ( 만년빙, 문스톤 포함 ), 1: 드랍템, 2: 희귀템 && 원본 템 ( ex. 생나, 운석, 포코(제작가능하지만 예외로 포함) )
    private Boolean restoreItemWhenResurrected; // 플레이어 부활 시, 인벤토리 템 유지 유무
    private Boolean autoDisappear;              // 바닥에 뿌려진 템, 자동 사라짐 유무
    private Boolean showInItemBook;             // 상점 내의 존재 유무

    private Double attackPower;        // 공격력
    private Double attackPowerByLv;    // 레벨당 공격력
    private Double defense;            // 방어력
    private Double defenseByLv;        // 레벨당 방어력
    private Double skillAmp;           // 스킬증폭
    private Double skillAmpByLevel;    // 레벨당 스킬증폭
    private Double skillAmpRatio;      // 스킬증폭 비율
    private Double skillAmpRatioByLevel;   // 레벨당 스킬증폭 비율
    private Double adaptiveForce;      // 적응형 능력치?
    private Double adaptiveForceByLevel;   // 레벨당 적응형 능력치
    private Double maxHp;      // 체력
    private Double maxHpByLv;  // 레벨당 체력
    private Double maxSp;      // 마나
    private Double hpRegenRatio;   // 체력 재생 (ex. 60% == 0.6)
    private Double spRegenRatio;   // 마나 재생 (체젠과 동일)
    private Double hpRegen;    // May not be used
    private Double spRegen;    // May not be used
    private Double attackSpeedRatio;        // 공격속도  (ex. 15% == 0.15)
    private Double attackSpeedRatioByLv;    // 레벨당 공격속도
    private Double criticalStrikeChance;    // 치명타 확률 (ex. 33% == 0.33)
    private Double criticalStrikeDamage;    // 치명타 피해량 (ex. 10% == 0.1)
    private Double preventCriticalStrikeDamaged;    // 치명타 감소
    private Double cooldownReduction;   // 쿨타임 감소
    private Double cooldownLimit;       // 최대 쿨타임
    private Double lifeSteal;           // 모든 피해흡혈
    private Double normalLifeSteal;     // 평타 피해흡혈
    private Double skillLifeSteal;      // 스킬 피해흡혈
    private Double moveSpeed;           // 이동속도
    private Double moveSpeedRatio;      // 레벨당 이동속도
    private Double moveSpeedOutOfCombat;// 비전투시 이동속도
    private Double sightRange;          // 시야
    private Double attackRange;         // 공격 범위
    private Double increaseBasicAttackDamage;       // 기본공격 데미지 증가
    private Double increaseBasicAttackDamageByLv;   // 레벨당 기본공격 데미지 증가
    private Double preventBasicAttackDamaged;       // 기본공격 피해 방어
    private Double preventBasicAttackDamagedByLv;   // 레벨당 기본공격 피해 방어
    private Double preventBasicAttackDamagedRatio;  // 기본공격 피해 방어 비율
    private Double preventBasicAttackDamagedRatioByLv;  // 레벨당 기본공격 피해 방어 비율
    private Double increaseBasicAttackDamageRatio;      // 기본 공격 데미지 증가 비율
    private Double increaseBasicAttackDamageRatioByLv;  // 레벨당 기본 공격 데미지 증가 비율
    private Double preventSkillDamaged;             // 스킬 데미지 방어
    private Double preventSkillDamagedByLv;         // 레벨당 스킬 데미지 방어
    private Double preventSkillDamagedRatio;        // 스킬 데미지 방어 비율
    private Double preventSkillDamagedRatioByLv;    // 레벨당 스킬 데미지 방어 비율
    private Double penetrationDefense;              // 방어관통 
    private Double penetrationDefenseRatio;         // 방어관통 비율
    private Double trapDamageReduce;                // 트랩 데미지 감소
    private Double trapDamageReduceRatio;           // 트랩 데미지 감소 비율
    private Double slowResistRatio;                 // 슬로우 저항 비율
    private Double hpHealedIncreaseRatio;           // 체력 회복 증가 비율
    private Double healerGiveHpHealRatio;           // 힐러 힐 체력 부여 비율

    //68
    /**
     * unique 는 아이템의 (고유)능력치 + @ 에 해당되는 스텟
     */
    private Double uniqueAttackRange;
    private Double uniqueHpHealedIncreaseRatio;
    private Double uniqueCooldownLimit;             // 최대 쿨다운 감소
    private Double uniqueTenacity;                  // 방해 효과 저항
    private Double uniqueMoveSpeed;
    private Double uniquePenetrationDefense;
    private Double uniquePenetrationDefenseRatio;
    private Double uniqueLifeSteal;
    private Double uniqueSkillAmpRatio;             // 스킬증폭 비율

//    private Integer modeType;
//    private String gradeBgOverride;
//    private Boolean alertInSpectator;
//    private String craftAnimTrigger;
//    private Integer stackable;
//    private Integer itemUsableValueList;
//    private Integer exclusiveProducer;
//    private String makeCustomAction;
//    private Boolean corpseEffectEnable;
//    private Boolean notDisarm;
//    private Boolean creditValueWhenConvertedToBounty;

}
