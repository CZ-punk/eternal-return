package demo.eternalreturn.domain.model;

import demo.eternalreturn.domain.model.constant.WeaponType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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
