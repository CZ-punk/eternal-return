package demo.eternalreturn.infrastructure.proxy.service.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

public interface JsonNodeService {

    JsonNode getJsonNodeByPathVariable(String path, String pathVariable, HttpMethod method, String rootNode);

    Mono<JsonNode> getMonoJsonNodeByPathVariable(String path, String pathVariable, HttpMethod method, String rootNode);

    JsonNode getJsonNodeByQueryParams(String path, Object queryParams, HttpMethod method, String rootNode);

//    String getStringDataFromNode(JsonNode node, String nodeName);
//    Integer getIntDataFromNode(JsonNode node, String nodeName);
//    Double getDoubleDataFromNode(JsonNode node, String nodeName);
//    Boolean getBooleanDataFromNode(JsonNode node, String nodeName);
}
