package demo.eternalreturn.presentation.dto.request.eternal_return;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemArmorCond {

    private Integer code;       // code 로 찾는게 의미가 있나..?
    private String name;        // like search based on name
    private String armorType;   // armorType == { "Head", "Chest", "Arm", "Leg" }

    private String itemGrade;       // itemGrade == { "Common", ... "Legend" }
    private Boolean itemGradeIsAsc; // Common~Legend || Legend~Common
    
    // TODO: at, de, hp, sp 순 정렬 ( 다른 조건도 고려 )

}
