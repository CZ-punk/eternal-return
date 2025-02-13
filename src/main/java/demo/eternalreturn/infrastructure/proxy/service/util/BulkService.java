package demo.eternalreturn.infrastructure.proxy.service.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;
import java.util.List;


public interface BulkService {


    <T> void comparingAndBulk(Iterator<JsonNode> elements, List<T> all, List<T> insertList, List<T> updateList, Class<T> clazz);

    <T> T createInstanceByObject(JsonNode dataNode, Class<T> clazz);



}
