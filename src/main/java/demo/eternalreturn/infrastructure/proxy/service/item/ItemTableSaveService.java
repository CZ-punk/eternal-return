package demo.eternalreturn.infrastructure.proxy.service.item;

import org.springframework.http.ResponseEntity;

public interface ItemTableSaveService {

    ResponseEntity<?> callItemMaterial();

    ResponseEntity<?> callItemConsumable();

}
