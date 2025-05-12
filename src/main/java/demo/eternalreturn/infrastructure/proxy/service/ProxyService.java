package demo.eternalreturn.infrastructure.proxy.service;

import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface ProxyService {
    <T> Mono<T> callApi(String endpoint, ReqApiDto request, Class<T> responseType);

    <T> Mono<List<T>> callApisWithRateLimit(Map<ReqApiDto, String> requestMap, Class<T> responseType);


}
