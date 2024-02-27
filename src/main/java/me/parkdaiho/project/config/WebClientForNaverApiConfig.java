package me.parkdaiho.project.config;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.SearchNaverNewsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Configuration
public class WebClientForNaverApiConfig {

    private final SearchNaverNewsProperties searchNaverNewsProperties;

    private final String NAVER_OPEN_API_BASE_URL = "https://openapi.naver.com";

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(NAVER_OPEN_API_BASE_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add("X-Naver-Client-Id", searchNaverNewsProperties.getClientId());
                    httpHeaders.add("X-Naver-Client-Secret", searchNaverNewsProperties.getClientSecret());
                })
                .build();
    }
}
