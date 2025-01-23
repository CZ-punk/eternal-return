package demo.eternalreturn.infrastructure.proxy.service.util;

import java.util.List;


public interface BulkService {

    <T> void bulkInsert(List<T> objectList);

    <T> void bulkUpdate(List<T> objectList);
}
