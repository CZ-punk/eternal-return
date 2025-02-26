package demo.eternalreturn.application.service;

import demo.eternalreturn.domain.repository.item.custom.ItemCustomRepository;
import demo.eternalreturn.presentation.dto.request.eternal_return.cond.ItemArmorCond;
import demo.eternalreturn.presentation.dto.request.eternal_return.cond.ItemConsumableCond;
import demo.eternalreturn.presentation.dto.request.eternal_return.cond.ItemWeaponCond;
import demo.eternalreturn.presentation.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static demo.eternalreturn.presentation.exception.ResultMessage.Success;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemCustomRepository itemCustomRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<?> searchItemConsumable(ItemConsumableCond cond) {
        return new ResponseDto<>(
                HttpStatus.OK,
                Success,
                itemCustomRepository.searchItemConsumable(cond)
        );
    }

    @Override
    public ResponseDto<?> searchItemArmor(ItemArmorCond cond) {
        return new ResponseDto<>(
                HttpStatus.OK,
                Success,
                itemCustomRepository.searchItemArmor(cond)
        );
    }

    @Override
    public ResponseDto<?> searchItemWeapon(ItemWeaponCond cond) {
        return new ResponseDto<>(
                HttpStatus.OK,
                Success,
                itemCustomRepository.searchItemWeapon(cond)
        );
    }
}
