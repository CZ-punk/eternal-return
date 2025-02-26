package demo.eternalreturn.presentation.dto.request.eternal_return.cond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemConsumableCond {

    private Integer code;
    private String name;
    private String itemGrade;
    private Boolean itemGradeIsAsc;

    // TODO: +hp, +sp, +at, +de, +skAmp 별 나눌 수 있는 field 고려

}
