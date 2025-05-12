package demo.eternalreturn.domain.repository.eternalreturn.item.jpa;

import demo.eternalreturn.domain.model.eternal_return.item.ItemWeapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemWeaponRepository extends JpaRepository<ItemWeapon, Integer> {
}
