package demo.eternalreturn.domain.model.eternal_return.item;

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
@Table(name = "item_material")
public class ItemMaterial {

    @Id
    private Integer code;
    private String name;

    private String itemGrade;                                               // 아이템 등급
    private String markingType;                                             // None or Unique
    private Integer initialCount;                                           // 드랍 시, 개수
    private Boolean isRemovedFromPlayerCorpseInventoryWhenPlayerKilled;     // 플레이어 사망 시, 시체 인벤토리 템 유지 유무
    private Boolean isCanMonsterAreaItemDrop;                               // 몬스터 드랍템 같은데 "가죽" 없음
    private Integer manufacturableType;                                     // 0: 제조템 ( 만년빙, 문스톤 포함 ), 1: 드랍템, 2: 희귀템 && 원본 템 ( ex. 생나, 운석, 포코(제작가능하지만 예외로 포함) )
    private Integer makeMaterial1;                                          // 재료1
    private Integer makeMaterial2;                                          // 재료2
    private Boolean restoreItemWhenResurrected;                             // 플레이어 부활 시, 인벤토리 템 유지 유무
    private Boolean autoDisappear;                                          // 바닥에 뿌려진 템, 자동 사라짐 유무
    private Boolean showInItemBook;                                         // 상점 내의 존재 유무

    public void update(ItemMaterial material) {
        this.name = material.getName();
        this.itemGrade = material.getItemGrade();
        this.markingType = material.getMarkingType();
        this.initialCount = material.getInitialCount();
        this.isRemovedFromPlayerCorpseInventoryWhenPlayerKilled = material.getIsRemovedFromPlayerCorpseInventoryWhenPlayerKilled();
        this.isCanMonsterAreaItemDrop = material.getIsCanMonsterAreaItemDrop();
        this.manufacturableType = material.getManufacturableType();
        this.makeMaterial1 = material.getMakeMaterial1();
        this.makeMaterial2 = material.getMakeMaterial2();
        this.restoreItemWhenResurrected = material.getRestoreItemWhenResurrected();
        this.autoDisappear = material.getAutoDisappear();
        this.showInItemBook = material.getShowInItemBook();
    }

}
