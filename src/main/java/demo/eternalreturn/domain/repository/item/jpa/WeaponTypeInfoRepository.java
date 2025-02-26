package demo.eternalreturn.domain.repository.item.jpa;

import demo.eternalreturn.domain.model.eternal_return.item.WeaponTypeInfo;
import demo.eternalreturn.domain.constant.WeaponType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponTypeInfoRepository extends JpaRepository<WeaponTypeInfo, WeaponType> {

}
