package demo.eternalreturn.infrastructure.proxy.service.item;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface ItemTableSaveService {

    ResponseEntity<?> callItemMaterial();

    ResponseEntity<?> callItemConsumable();

    Mono<?> callItemArmor();


//    ResponseEntity<?> callItemArmor();
}
