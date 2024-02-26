package me.parkdaiho.project.repository.user;

import me.parkdaiho.project.dto.article.ArticleItem;
import me.parkdaiho.project.dto.article.SearchArticleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

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

        Mono<SearchArticleResponse> response = webClient.get()
                .uri(baseUrl, params)
                .retrieve()
                .bodyToMono(SearchArticleResponse.class);

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
        String url = "https://openapi.naver.com/v1/search/news.json";
        String searchParam = URLEncoder.encode("이강인", StandardCharsets.UTF_8);

    }
}
