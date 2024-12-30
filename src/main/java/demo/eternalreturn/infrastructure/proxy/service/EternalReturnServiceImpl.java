package demo.eternalreturn.infrastructure.proxy.service;

import demo.eternalreturn.infrastructure.proxy.dto.request.ReqApiDto;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EternalReturnServiceImpl implements EternalReturnService {

    @Autowired
    private final WebClient webClient;

    @Override
    public <T> Mono<T> callApi(String endpoint, ReqApiDto request, Class<T> responseType) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(endpoint);

        if (request.getQueryParams() != null) {
            request.getQueryParams().entrySet().stream()
                    .filter(entry -> entry.getValue() != null)
                    .forEach(entry -> uriBuilder.queryParam(entry.getKey(), entry.getValue()));
        }

        String uri = uriBuilder.build().toUriString();
        log.debug("Constructed URI: {}", uri);

        HttpMethod method = request.getMethod();
        WebClient.RequestBodySpec headersSpec = webClient
                .method(method)
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON);


        if (method == HttpMethod.POST || method == HttpMethod.PUT || method == HttpMethod.PATCH) {
            headersSpec = headersSpec.contentType(MediaType.APPLICATION_JSON);

            if (request.getRequestBody() != null) {
                headersSpec = (WebClient.RequestBodySpec) headersSpec.bodyValue(request.getRequestBody());
            }
        }

        return headersSpec.retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new CustomException(clientResponse.statusCode(), errorBody))))

                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new CustomException(clientResponse.statusCode(), errorBody))))

                .bodyToMono(responseType)
                .timeout(Duration.ofSeconds(10))
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2))
                        .filter(throwable -> throwable instanceof TimeoutException || throwable instanceof ServerErrorException))
                .doOnError(e -> log.error("Error during API call: ", e));
    }

}
