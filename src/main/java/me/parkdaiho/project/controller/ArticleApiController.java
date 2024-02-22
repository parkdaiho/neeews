package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.CommentTestRequest;
import me.parkdaiho.project.dto.CommentTestResponse;
import me.parkdaiho.project.dto.TestRequest;
import me.parkdaiho.project.dto.TestResponse;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/new-article")
    public ResponseEntity<TestResponse> newArticle(@RequestBody TestRequest dto) {
        return ResponseEntity.ok()
                .body(articleService.newArticle(dto));
    }

    @PostMapping("/api/new-comment")
    public ResponseEntity<List<CommentTestResponse>> newComment(@RequestBody CommentTestRequest dto,
                                                @AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok()
                .body(articleService.newComment(dto, principal));
    }
}
