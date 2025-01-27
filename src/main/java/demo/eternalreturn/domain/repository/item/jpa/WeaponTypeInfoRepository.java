package demo.eternalreturn.domain.repository.item.jpa;

import demo.eternalreturn.domain.model.WeaponTypeInfo;
import demo.eternalreturn.domain.model.constant.WeaponType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponTypeInfoRepository extends JpaRepository<WeaponTypeInfo, WeaponType> {

}
