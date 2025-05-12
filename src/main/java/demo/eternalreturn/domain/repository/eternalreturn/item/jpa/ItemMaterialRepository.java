package demo.eternalreturn.domain.repository.eternalreturn.item.jpa;

import demo.eternalreturn.domain.model.eternal_return.item.ItemMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemMaterialRepository extends JpaRepository<ItemMaterial, Integer> {
}
