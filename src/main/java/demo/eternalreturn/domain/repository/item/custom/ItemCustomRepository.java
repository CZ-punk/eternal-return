package demo.eternalreturn.domain.repository.item.custom;

import demo.eternalreturn.presentation.dto.request.eternal_return.ItemArmorCond;
import demo.eternalreturn.presentation.dto.request.eternal_return.ItemConsumableCond;
import demo.eternalreturn.presentation.dto.request.eternal_return.ItemWeaponCond;
import demo.eternalreturn.presentation.dto.response.eternal_return.item.ResItemArmorDto;
import demo.eternalreturn.presentation.dto.response.eternal_return.item.ResItemConsumableDto;
import demo.eternalreturn.presentation.dto.response.eternal_return.item.ResItemWeaponDto;

import java.util.List;

public interface ItemCustomRepository {

    List<ResItemConsumableDto> searchItemConsumable(ItemConsumableCond cond);

    List<ResItemArmorDto> searchItemArmor(ItemArmorCond cond);

    List<ResItemWeaponDto> searchItemWeapon(ItemWeaponCond cond);

}
