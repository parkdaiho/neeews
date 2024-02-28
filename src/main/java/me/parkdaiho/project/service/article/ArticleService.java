package me.parkdaiho.project.service.article;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.dto.article.ArticleViewRequest;
import me.parkdaiho.project.dto.article.ArticleViewResponse;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import me.parkdaiho.project.repository.article.ArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleConsumer;

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

    private Article findArticleByLink(ArticleViewRequest dto) throws IOException {
        Article article = articleRepository.findByLink(dto.getLink())
                .orElse(dto.toEntity());

        if(article.getId() != null) {
            return article;
        }

        if(!article.getLink().equals(article.getOriginalLink())) {
            Map<String, String> contents = getContentsByLink(article.getLink());

            article.setImgLink(contents.get("imgLink"));
            article.setTexts(contents.get("texts"));
        }

        return articleRepository.save(article);
    }

    private Map<String, String> getContentsByLink(String link) throws IOException {
        Map<String, String> contents = new HashMap<>();

        Document document = Jsoup.connect(link)
                .header(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36")
                .get();
        Element imgLink = document.getElementById("img1");
        Element texts = document.getElementById("dic_area")
                .removeClass("end_photo_org");

        contents.put("texts", texts.html());
        contents.put("imgLink", imgLink.attr("src"));

        return contents;
    }

    public Long getArticleId(ArticleViewRequest dto) throws IOException {
        Article article = findArticleByLink(dto);

        return article.getId();
    }

    public ArticleViewResponse getArticleById(Long id) {
        Article article = findArticleById(id);

        return new ArticleViewResponse(article);
    }

    private Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected articleId: " + id));
    }
}
