package demo.eternalreturn.infrastructure.proxy.service.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.List;

public interface JsonNodeService {

    JsonNode getJsonNodeByPathVariable(String path, String pathVariable, HttpMethod method, String rootNode);

    Mono<JsonNode> getMonoJsonNodeByPathVariable(String path, String pathVariable, HttpMethod method);

    JsonNode getJsonNodeByQueryParams(String path, Object queryParams, HttpMethod method, String rootNode);

    Mono<JsonNode> getMonoJsonNodeByQueryParams(String path, Object queryParams, HttpMethod method);

    JsonNode checkNodeByName(JsonNode node, String nodeName);

    <T> T extractDto(JsonNode node, Class<T> clazz);

    <T> List<T> extractDtoList(JsonNode node, Class<T> clazz);

    <T> T jsonMapping(Iterator<JsonNode> elements, Class<T> clazz);

}
