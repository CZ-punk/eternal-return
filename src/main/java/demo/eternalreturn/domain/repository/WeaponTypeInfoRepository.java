package demo.eternalreturn.domain.repository;

import demo.eternalreturn.domain.model.WeaponTypeInfo;
import demo.eternalreturn.domain.model.constant.WeaponType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WeaponTypeInfoRepository extends JpaRepository<WeaponTypeInfo, WeaponType> {

}
