package me.parkdaiho.project.repository.user;

import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WebClientTest {

    @Autowired
    WebClient webClient;

    @Test
    public void apiTest() {
        String baseUrl = "https://openapi.naver.com";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "이강인");

        UriComponents uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/v1/search/news.json")
                .queryParams(params)
                .build();

        Mono<SearchNaverNewsResponse> response = webClient.get()
                .uri(baseUrl, params)
                .retrieve()
                .bodyToMono(SearchNaverNewsResponse.class);

        response.doOnSuccess(res -> {
            res.getItems().stream()
                    .forEach(item -> {
                        System.out.println(item.getTitle());
                    });
        });
    }

    @Test
    public void urlTest() {
        String url = "https://openapi.naver.com/v1/search/news.json";
        String searchParam = URLEncoder.encode("이강인", StandardCharsets.UTF_8);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", searchParam);

        UriComponents uri = UriComponentsBuilder.fromUriString(url)
                .queryParams(params)
                .build();

        System.out.println(uri.toUriString());
    }

    @Test
    public void urlTest2() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("key", "value");

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com") // 도메인 문자열을 인자로 받는다.
                .path("/v1/search/news.json") // 경로에 해당하는 문자열을 인자로 받는다.
                .queryParams(params) // 파라미터를 인자로 받는다.
                .build()
                .toUri();

        System.out.println(uri.toString());
    }
}
