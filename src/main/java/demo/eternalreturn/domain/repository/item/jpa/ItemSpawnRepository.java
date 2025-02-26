package demo.eternalreturn.domain.repository.item.jpa;

import demo.eternalreturn.domain.model.eternal_return.item.ItemSpawn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSpawnRepository extends JpaRepository<ItemSpawn, Integer> {
}
