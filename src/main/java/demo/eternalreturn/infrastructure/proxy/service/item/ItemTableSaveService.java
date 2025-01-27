package demo.eternalreturn.infrastructure.proxy.service.item;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface ItemTableSaveService {

    Mono<?> callItemMaterial();

    Mono<?> callItemConsumable();

    Mono<?> callItemArmor();

    Mono<?> callItemWeapon();

    Mono<?> callItemSpecial();

    Mono<?> callItemSpawn();

    Mono<?> callItemSearchOptionV2();
}
