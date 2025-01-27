package demo.eternalreturn.infrastructure.proxy.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import demo.eternalreturn.infrastructure.proxy.service.EternalReturnService;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static demo.eternalreturn.infrastructure.proxy.service.util.InstanceUtils.createInstance;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonNodeServiceImpl implements JsonNodeService {

    @Autowired
    private final EternalReturnService eternalReturnService;
    @Value("${eternal-return.url}")
    private String baseUrl;
    private ObjectMapper objectMapper = new ObjectMapper();

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
    public Mono<JsonNode> getMonoJsonNodeByPathVariable(String path, String pathVariable, HttpMethod method) {
        ReqApiDto request = new ReqApiDto();
        request.setPathVariable("/" + pathVariable);
        request.setMethod(method);
        String endpoint = baseUrl + path;
        endpoint += "/" + pathVariable;

        return eternalReturnService.callApi(endpoint, request, JsonNode.class);
    }

    @Override
    public JsonNode getJsonNodeByQueryParams(String path, Object queryParams, HttpMethod method, String rootNode) {
        ReqApiDto request = new ReqApiDto();
        request.setQueryParams(QueryParamUtils.convertToQueryParams(queryParams));
        request.setMethod(method);
        String endpoint = baseUrl + path;

        return eternalReturnService.callApi(endpoint, request, JsonNode.class).block().path(rootNode);
    }

    @Override
    public Mono<JsonNode> getMonoJsonNodeByQueryParams(String path, Object queryParams, HttpMethod method) {
        ReqApiDto request = new ReqApiDto();
        request.setQueryParams(QueryParamUtils.convertToQueryParams(queryParams));
        request.setMethod(method);
        String endpoint = baseUrl + path;

        return eternalReturnService.callApi(endpoint, request, JsonNode.class);
    }

    @Override
    public JsonNode checkNodeByName(JsonNode node, String nodeName) {
        JsonNode result = node.path(nodeName);
        if (result == null) throw new CustomException(HttpStatus.CONFLICT, "Node for that data does not exist / nodeName: " + nodeName);
        return result;
    }

    @Override
    public <T> T extractDto(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during extraction");
        }
    }

    @Override
    public <T> List<T> extractDtoList(JsonNode node, Class<T> clazz) {
        try {
            List<T> resultList = new ArrayList<>();
            for (JsonNode data : node) {
                T dto = objectMapper.treeToValue(data, clazz);
                resultList.add(dto);
            }
            return resultList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during extraction");
        }
    }

    @Override
    public <T> T jsonMapping(Iterator<JsonNode> elements, Class<T> clazz) {
        while (elements.hasNext()) {
            JsonNode element = elements.next();
            try {
                T item = objectMapper.readValue(element.toString(), clazz);
                setRelationships(item, element);
                return item;

            } catch (IOException e) {
                log.error("JSON 변환 중 오류 발생: {}", e.getMessage());
                throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
        return null;
    }

    private <T> void setRelationships(T item, JsonNode element) {
        try {
            Field[] declaredFields = item.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);

                if (List.class.isAssignableFrom(field.getType())) {
                    ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                    Class<?> listType = (Class<?>) genericType.getActualTypeArguments()[0];

                    JsonNode value = element.path(field.getName());
                    for (JsonNode node : value) {
                        Object o = objectMapper.readValue(node.toString(), listType);
                        item.getClass().getMethod("add" + listType.getSimpleName(), listType).invoke(item, o);
                    }
                }
            }
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "setRelationShips Err");
        }
    }

}
