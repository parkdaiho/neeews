package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.article.*;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/clip/{id}")
    public ResponseEntity<Void> clipArticle(@PathVariable Long id,
                                            @AuthenticationPrincipal PrincipalDetails principal) {
        articleService.clippingArticle(id, principal);

        return ResponseEntity.created(URI.create("/clippings")).build();
    }
}