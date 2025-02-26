package demo.eternalreturn.domain.model.eternal_return.item;

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
@Table(name = "item_special")
public class ItemSpecial {

    @Id
    private Integer code;
    private String name;
    private Integer modeType;
    private String itemType;
    private String specialItemType;
    private String itemGrade;
    private Boolean isCompletedItem;
    private Boolean alertInSpectator;
    private String markingType;
    private Integer initialCount;
    private String cooldownGroupType;
    private Integer cooldown;
    private String itemUsableType;
    private Integer itemUsableValueList;
    private Integer exclusiveProducer;
    private Boolean isRemovedFromPlayerCorpseInventoryWhenPlayerKilled;
    private Boolean isCanMonsterAreaItemDrop;
    private Integer manufacturableType;
    private Integer makeMaterial1;
    private Integer makeMaterial2;

    private Integer consumeCount;
    private Integer summonCode;
    private Integer ghostItemStateGroup;
    private Boolean restoreItemWhenResurrected;
    private Integer creditValueWhenConvertedToBounty;
    private Boolean isReduceLootOnDeath;
    private Boolean autoDisappear;
    private Boolean showInItemBook;


}
