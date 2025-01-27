package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.service.ItemService;
import demo.eternalreturn.presentation.controller.dto.request.cond.ItemArmorCond;
import demo.eternalreturn.presentation.controller.dto.request.cond.ItemConsumableCond;
import demo.eternalreturn.presentation.controller.dto.request.cond.ItemWeaponCond;
import demo.eternalreturn.presentation.controller.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {

    @Autowired
    private final ItemService itemService;

    /**
     * Search Item Consumable ( cond )
     * cond = { eq code, like name, eq itemGrade, sort itemGradeIsAsc }
     */
    @GetMapping("/consumable")
    public ResponseDto<?> searchItemConsumable(@ModelAttribute ItemConsumableCond cond) {
        return itemService.searchItemConsumable(cond);
    }

    /**
     * Search Item Armor ( cond )
     * cond = { eq code, like name, eq armorType, eq itemGrade, sort itemGradeIsAsc }
     */
    @GetMapping("/armor")
    public ResponseDto<?> searchItemArmor(@ModelAttribute ItemArmorCond cond) {
        return itemService.searchItemArmor(cond);
    }

    /**
     * Search Weapon ( cond )
     *
     *
     */
    @GetMapping("/weapon")
    public ResponseDto<?> searchItemWeapon(@ModelAttribute ItemWeaponCond cond) {
        return itemService.searchItemWeapon(cond);
    }

    /**
     *
     */


    // 소모품, 무기, 머리, 몸, 팔, 신발
    // 각각의 정보만 전달
    // TODO: Item 관련 컨트롤러 생성 후, Item Type 별 목록 내려주는 Method 구현
}
