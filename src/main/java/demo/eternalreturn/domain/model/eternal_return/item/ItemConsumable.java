package demo.eternalreturn.domain.model.eternal_return.item;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_consumable")
public class ItemConsumable {

    @Id
    private Integer code;
    private String name;


    private String consumableType;                                          // 소비 타입 ( ex, Beverage )
    private String itemType;
    private String itemGrade;                                               // 아이템 등급
    private Integer initialCount;                                           // 드랍 시, 개수
    private Boolean isRemovedFromPlayerCorpseInventoryWhenPlayerKilled;     // 플레이어 사망 시, 시체 인벤토리 템 유지 유무
    private Boolean isCanMonsterAreaItemDrop;                               // 몬스터 드랍템 같은데 "가죽" 없음
    private Integer manufacturableType;                                     // 0: 제조템 ( 만년빙, 문스톤 포함 ), 1: 드랍템, 2: 희귀템 && 원본 템 ( ex. 생나, 운석, 포코(제작가능하지만 예외로 포함) )
    private Boolean isCompletedItem;
    private Integer makeMaterial1;                                          // 재료1
    private Integer makeMaterial2;                                          // 재료2
    private Boolean restoreItemWhenResurrected;                             // 플레이어 부활 시, 인벤토리 템 유지 유무
    private Boolean autoDisappear;                                          // 바닥에 뿌려진 템, 자동 사라짐 유무
    private Boolean showInItemBook;                                         // 상점 내의 존재 유무

    private Double heal;
    private Double hpRecover;
    private Double spRecover;
    private Double attackPowerByBuff;
    private Double defenseByBuff;
    private Double skillAmpByBuff;
    private Double skillAmpRatioByBuff;
    private Double addStateCode;
    private Double isReduceLootOnDeath;

    public void update(ItemConsumable consumable) {
        if (consumable == null) {
            return;
        }

        this.name = consumable.getName();
        this.consumableType = consumable.getConsumableType();
        this.itemGrade = consumable.getItemGrade();
        this.initialCount = consumable.getInitialCount();
        this.isRemovedFromPlayerCorpseInventoryWhenPlayerKilled = consumable.getIsRemovedFromPlayerCorpseInventoryWhenPlayerKilled();
        this.isCanMonsterAreaItemDrop = consumable.getIsCanMonsterAreaItemDrop();
        this.manufacturableType = consumable.getManufacturableType();
        this.makeMaterial1 = consumable.getMakeMaterial1();
        this.makeMaterial2 = consumable.getMakeMaterial2();
        this.restoreItemWhenResurrected = consumable.getRestoreItemWhenResurrected();
        this.autoDisappear = consumable.getAutoDisappear();
        this.showInItemBook = consumable.getShowInItemBook();

        this.heal = consumable.getHeal();
        this.hpRecover = consumable.getHpRecover();
        this.spRecover = consumable.getSpRecover();
        this.attackPowerByBuff = consumable.getAttackPowerByBuff();
        this.defenseByBuff = consumable.getDefenseByBuff();
        this.skillAmpByBuff = consumable.getSkillAmpByBuff();
        this.skillAmpRatioByBuff = consumable.getSkillAmpRatioByBuff();
        this.addStateCode = consumable.getAddStateCode();
        this.isReduceLootOnDeath = consumable.getIsReduceLootOnDeath();
    }


}
