package demo.eternalreturn.domain.repository.item.custom;

import demo.eternalreturn.presentation.controller.dto.request.cond.ItemArmorCond;
import demo.eternalreturn.presentation.controller.dto.request.cond.ItemConsumableCond;
import demo.eternalreturn.presentation.controller.dto.request.cond.ItemWeaponCond;
import demo.eternalreturn.presentation.controller.dto.response.item.ResItemArmorDto;
import demo.eternalreturn.presentation.controller.dto.response.item.ResItemConsumableDto;
import demo.eternalreturn.presentation.controller.dto.response.item.ResItemWeaponDto;

import java.util.List;

public interface ItemCustomRepository {

    List<ResItemConsumableDto> searchItemConsumable(ItemConsumableCond cond);

    List<ResItemArmorDto> searchItemArmor(ItemArmorCond cond);

    List<ResItemWeaponDto> searchItemWeapon(ItemWeaponCond cond);

}
