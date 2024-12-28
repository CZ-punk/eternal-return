package demo.eternalreturn.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${eternal-return.header}")
    private String headerName;
    @Value("${eternal-return.key}")
    private String apiKey;
    @Value("${eternal-return.url}")
    private String baseUrl;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(baseUrl)
                .defaultHeaders(headers -> {
                    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    headers.add(headerName, apiKey);
                })
                .build();

        // TODO: codecs, Factory refactoring
    }


}
