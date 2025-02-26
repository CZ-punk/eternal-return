package demo.eternalreturn.domain.repository.item.jpa;

import demo.eternalreturn.domain.model.eternal_return.item.ItemSpecial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSpecialRepository extends JpaRepository<ItemSpecial, Integer> {
}
