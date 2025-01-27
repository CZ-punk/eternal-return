package demo.eternalreturn.application.service;

import demo.eternalreturn.presentation.controller.dto.request.cond.ItemArmorCond;
import demo.eternalreturn.presentation.controller.dto.request.cond.ItemConsumableCond;
import demo.eternalreturn.presentation.controller.dto.request.cond.ItemWeaponCond;
import demo.eternalreturn.presentation.controller.dto.response.ResponseDto;

public interface ItemService {

    ResponseDto<?> searchItemConsumable(ItemConsumableCond cond);

    ResponseDto<?> searchItemArmor(ItemArmorCond cond);

    ResponseDto<?> searchItemWeapon(ItemWeaponCond cond);

//    ResponseDto<?> searchItemWeapon(ItemWeaponCond cond);
}
