package demo.eternalreturn.infrastructure.proxy.service.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;

public interface JsonNodeService {

    JsonNode getJsonNodeByPathVariable(String path, String pathVariable, HttpMethod method, String rootNode);

    JsonNode getJsonNodeByQueryParams(String path, Object queryParams, HttpMethod method, String rootNode);

}
