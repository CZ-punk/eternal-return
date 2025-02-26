package demo.eternalreturn.domain.model.eternal_return.item;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "weapon_type_info")
public class WeaponTypeInfo {

    @Id
    private String type;

    private Double attackSpeed;
    private Double attackRange;
    private Double shopFilter;
    private Double summonObjectHitDamage;

}
