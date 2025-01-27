package demo.eternalreturn.presentation.controller.dto.request.cond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemWeaponCond {

    private Integer code;           // code 로 찾는게 의미가 있나..?
    private String name;            // like search based on name
    private String weaponType;      // many..

    private String itemGrade;       // itemGrade == { "Common", ... "Legend", "Mythic" }
    private Boolean itemGradeIsAsc; // Common~Mythic || Mythic~Common

    // TODO: at, de, hp, sp 순 정렬 ( 다른 조건도 고려 )
}
