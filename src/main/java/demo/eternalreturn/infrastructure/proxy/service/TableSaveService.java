package demo.eternalreturn.infrastructure.proxy.service;

import org.springframework.http.ResponseEntity;

public interface TableSaveService {
    ResponseEntity<?> callWeaponTypeInfo();

    ResponseEntity<?> callExperiment();
    ResponseEntity<?> callExperimentAttribute();
}
