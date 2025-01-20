package demo.eternalreturn.infrastructure.proxy.service.util;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import demo.eternalreturn.infrastructure.proxy.service.EternalReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonNodeServiceImpl implements JsonNodeService {

    @Autowired
    private final EternalReturnService eternalReturnService;
    @Value("${eternal-return.url}")
    private String baseUrl;

    @Override
    public JsonNode getJsonNodeByPathVariable(String path, String pathVariable, HttpMethod method, String rootNode) {
        ReqApiDto request = new ReqApiDto();
        request.setPathVariable("/" + pathVariable);
        request.setMethod(method);
        String endpoint = baseUrl + path;
        endpoint += "/" + pathVariable;

        return eternalReturnService.callApi(endpoint, request, JsonNode.class).block().path(rootNode);
    }

    @Override
    public JsonNode getJsonNodeByQueryParams(String path, Object queryParams, HttpMethod method, String rootNode) {
        ReqApiDto request = new ReqApiDto();
        request.setQueryParams(QueryParamUtils.convertToQueryParams(queryParams));
        request.setMethod(method);
        String endpoint = baseUrl + path;

        return eternalReturnService.callApi(endpoint, request, JsonNode.class).block().path(rootNode);
    }
}
