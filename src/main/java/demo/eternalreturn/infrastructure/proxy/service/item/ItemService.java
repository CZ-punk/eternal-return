package demo.eternalreturn.infrastructure.proxy.service.item;

import demo.eternalreturn.presentation.dto.request.eternal_return.ItemArmorCond;
import demo.eternalreturn.presentation.dto.request.eternal_return.ItemConsumableCond;
import demo.eternalreturn.presentation.dto.request.eternal_return.ItemWeaponCond;
import demo.eternalreturn.presentation.dto.response.ResponseDto;

public interface ItemService {

    ResponseDto<?> searchItemConsumable(ItemConsumableCond cond);

    ResponseDto<?> searchItemArmor(ItemArmorCond cond);

    ResponseDto<?> searchItemWeapon(ItemWeaponCond cond);

//    ResponseDto<?> searchItemWeapon(ItemWeaponCond cond);
}
