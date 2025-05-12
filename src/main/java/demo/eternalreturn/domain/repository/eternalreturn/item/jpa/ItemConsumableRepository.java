package demo.eternalreturn.domain.repository.eternalreturn.item.jpa;

import demo.eternalreturn.domain.model.eternal_return.item.ItemConsumable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemConsumableRepository extends JpaRepository<ItemConsumable, Integer> {
}
