package me.parkdaiho.project.service.article;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.dto.article.ArticleViewRequest;
import me.parkdaiho.project.dto.article.ArticleViewResponse;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import me.parkdaiho.project.repository.article.ArticleRepository;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final NaverNewsCrawler naverNewsCrawler;

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

    private Article findArticleByLink(ArticleViewRequest dto) throws IOException {
        Article article = articleRepository.findByLink(dto.getLink())
                .orElse(dto.toEntity());

        if(article.getId() != null) {
            return article;
        }

        if(!article.getLink().equals(article.getOriginalLink())) {
            String contents = getContentsByLink(article.getLink());

            article.setContents(contents);
        }

        return articleRepository.save(article);
    }

    private String getContentsByLink(String link) throws IOException {
        return naverNewsCrawler.getContents(link);
    }

    public Long getArticleId(ArticleViewRequest dto) throws IOException {
        Article article = findArticleByLink(dto);

        return article.getId();
    }

    public ArticleViewResponse getArticleView(Long id, HttpServletRequest request, HttpServletResponse response) {
        Article article = findArticleById(id);

        if(!CookieUtils.checkView(request, response, Domain.ARTICLE, id)) article.addViews();

        return new ArticleViewResponse(article);
    }

    public Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected articleId: " + id));
    }
}
