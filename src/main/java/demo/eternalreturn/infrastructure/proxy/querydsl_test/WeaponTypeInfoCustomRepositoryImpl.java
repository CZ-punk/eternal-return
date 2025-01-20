package demo.eternalreturn.infrastructure.proxy.querydsl_test;

import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.eternalreturn.domain.model.WeaponTypeInfo;
import demo.eternalreturn.domain.model.constant.WeaponType;
import demo.eternalreturn.presentation.exception.CustomException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

import static demo.eternalreturn.domain.model.QWeaponTypeInfo.weaponTypeInfo;

@Repository
@RequiredArgsConstructor
public class WeaponTypeInfoCustomRepositoryImpl implements WeaponTypeInfoCustomRepository {

    @Autowired
    private final JPAQueryFactory queryFactory;

    @Override
    public WeaponTypeInfo findWeaponTypeInfo(WeaponType type) {

        WeaponTypeInfo findWeapon = queryFactory
                .selectFrom(weaponTypeInfo)
                .where(weaponTypeInfo.type.eq(type))
                .fetchOne();

        if (findWeapon == null) throw new CustomException(HttpStatus.BAD_REQUEST, "not found weapon type info by type");


        long execute = queryFactory
                .update(weaponTypeInfo)
                .set(weaponTypeInfo.attackRange, 99.9999)
                .where(weaponTypeInfo.type.eq(type))
                .execute();

        System.out.println("execute = " + execute);

        findWeapon.setAttackRange(555.555);

//        em.flush();
//        em.clear();

        List<WeaponTypeInfo> result = queryFactory
                .selectFrom(weaponTypeInfo)
                .fetch();

        for (WeaponTypeInfo typeInfo : result) {
            System.out.println("typeInfo = " + typeInfo);
        }

        return null;
    }
}
