package demo.eternalreturn.infrastructure.proxy.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface BulkService {

    <T> void bulkInsert(List<T> objectList);
}
