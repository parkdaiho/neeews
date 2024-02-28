package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.article.ArticleViewRequest;
import me.parkdaiho.project.dto.article.ArticleViewResponse;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/article")
    public ResponseEntity<Void> getArticle(@RequestBody ArticleViewRequest dto) throws IOException {
        Long articleId = articleService.getArticleId(dto);

        return ResponseEntity.created(URI.create("/articles/" + articleId)).build();
    }
}
