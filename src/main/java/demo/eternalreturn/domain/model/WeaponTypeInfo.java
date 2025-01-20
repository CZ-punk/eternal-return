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
    @Enumerated(EnumType.STRING)
    private WeaponType type;

    private Double attackSpeed;
    private Double attackRange;
    private Double shopFilter;
    private Double summonObjectHitDamage;

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        WeaponTypeInfo that = (WeaponTypeInfo) obj;
//
//        return Objects.equals(type, that.type) &&
//                Objects.equals(attackSpeed, that.attackSpeed) &&
//                Objects.equals(attackRange, that.attackRange) &&
//                Objects.equals(shopFilter, that.shopFilter) &&
//                Objects.equals(summonObjectHitDamage, that.summonObjectHitDamage);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(
//                type, attackSpeed, attackRange, shopFilter, summonObjectHitDamage
//        );
//    }

}
