package demo.eternalreturn.infrastructure.proxy.querydsl_test;

import demo.eternalreturn.domain.model.constant.WeaponType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryDslTestService {

    @Autowired
    private final WeaponTypeInfoCustomRepository weaponTypeInfoCustomRepository;

    @Transactional
    public String test() {
        weaponTypeInfoCustomRepository.findWeaponTypeInfo(WeaponType.Glove);
        return "success";
    }
}
