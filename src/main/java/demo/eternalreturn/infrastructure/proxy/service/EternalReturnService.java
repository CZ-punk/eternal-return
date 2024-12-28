package demo.eternalreturn.infrastructure.proxy.service;

import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import reactor.core.publisher.Mono;

public interface EternalReturnService {
    <T> Mono<T> callApi(String endpoint, ReqApiDto request, Class<T> responseType);
}
