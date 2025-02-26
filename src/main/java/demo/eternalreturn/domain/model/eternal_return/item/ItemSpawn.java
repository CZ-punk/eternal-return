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
@ToString
@Table(name = "item_spawn")
public class ItemSpawn {

    @Id
    private Integer code;

    private Integer areaCode;           // 지역 별 고유 코드
    private Integer areaSpawnGroup;     // 지역 내.. 위치 별 분포..?
    private Integer itemCode;           // 아이템 코드 ( item_materials )
    private Integer dropCount;          // 아이템 드랍 가능 갯수

}
