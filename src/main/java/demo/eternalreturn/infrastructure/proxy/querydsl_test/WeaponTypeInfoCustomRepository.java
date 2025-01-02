package demo.eternalreturn.infrastructure.proxy.querydsl_test;

import demo.eternalreturn.domain.model.WeaponTypeInfo;
import demo.eternalreturn.domain.model.constant.WeaponType;

public interface WeaponTypeInfoCustomRepository {

    WeaponTypeInfo findWeaponTypeInfo(WeaponType type);

}
