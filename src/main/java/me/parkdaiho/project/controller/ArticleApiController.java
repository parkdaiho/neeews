package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.article.ArticleViewRequest;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<Void> articleView(@RequestBody ArticleViewRequest dto) {
        Long articleId = articleService.findArticle(dto);

        return ResponseEntity.created(URI.create("/articles/" + articleId)).build();
    }
}
