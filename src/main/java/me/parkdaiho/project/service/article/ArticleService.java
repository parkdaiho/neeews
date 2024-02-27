package me.parkdaiho.project.service.article;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.dto.article.ArticleViewRequest;
import me.parkdaiho.project.dto.article.ArticleViewResponse;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import me.parkdaiho.project.repository.article.ArticleRepository;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public SearchNaverNewsResponse getSearchResult(SearchNaverNewsRequest dto) {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/api/naver-news")
                .build().toUri();

        RequestEntity<SearchNaverNewsRequest> request = RequestEntity.post(uri)
                .body(dto);

        ResponseEntity<SearchNaverNewsResponse> response = restTemplate.exchange(request, SearchNaverNewsResponse.class);
        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException("fait to search news");
        }

        return response.getBody();
    }
}
