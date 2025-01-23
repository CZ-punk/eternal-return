package demo.eternalreturn.domain.repository.item.jpa;

import demo.eternalreturn.domain.model.item.ItemArmor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface ItemArmorRepository extends JpaRepository<ItemArmor, Integer> {

}
