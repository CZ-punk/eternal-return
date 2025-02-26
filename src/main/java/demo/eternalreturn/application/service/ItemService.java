package demo.eternalreturn.application.service;

import demo.eternalreturn.presentation.dto.request.eternal_return.cond.ItemArmorCond;
import demo.eternalreturn.presentation.dto.request.eternal_return.cond.ItemConsumableCond;
import demo.eternalreturn.presentation.dto.request.eternal_return.cond.ItemWeaponCond;
import demo.eternalreturn.presentation.dto.response.ResponseDto;

public interface ItemService {

    ResponseDto<?> searchItemConsumable(ItemConsumableCond cond);

    ResponseDto<?> searchItemArmor(ItemArmorCond cond);

    ResponseDto<?> searchItemWeapon(ItemWeaponCond cond);

//    ResponseDto<?> searchItemWeapon(ItemWeaponCond cond);
}
