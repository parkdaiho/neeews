package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleImage;
import me.parkdaiho.project.dto.TestRequest;
import me.parkdaiho.project.dto.TestResponse;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/new-article")
    public ResponseEntity<TestResponse> newArticle(@RequestBody TestRequest dto) {
        Article savedArticle = articleService.newArticle(dto);
        ArticleImage articleImage = savedArticle.getArticleImages().get(0);

        return ResponseEntity.ok()
                .body(new TestResponse(savedArticle, articleImage));
    }
}
