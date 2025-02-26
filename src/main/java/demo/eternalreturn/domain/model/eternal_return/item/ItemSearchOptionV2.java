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
@Table(name = "item_search_option_v2")
public class ItemSearchOptionV2 {

    @Id
    private Integer code;
    private String name;
    private String tag1;
    private String tag2;
    private String tag3;
}
