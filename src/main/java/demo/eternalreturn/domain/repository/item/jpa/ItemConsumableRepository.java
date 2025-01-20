package demo.eternalreturn.domain.repository.item.jpa;

import demo.eternalreturn.domain.model.item.ItemConsumable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemConsumableRepository extends JpaRepository<ItemConsumable, Integer> {
}
