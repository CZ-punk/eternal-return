package demo.eternalreturn.presentation.dto.response.eternal_return.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResItemConsumableDto {

    private Integer code;
    private String name;

    private String itemType;
    private String itemGrade;

    private Boolean isCompletedItem;
    private Integer makeMaterial1;
    private Integer makeMaterial2;

    private Double hpRecover;
    private Double spRecover;

}
