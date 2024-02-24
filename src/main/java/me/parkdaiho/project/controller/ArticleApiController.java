package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.article.*;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/new-article")
    public ResponseEntity<AddArticleResponse> newArticle(@RequestBody AddArticleRequest dto) {
        return ResponseEntity.ok()
                .body(articleService.newArticle(dto));
    }

    @PostMapping("/api/new-comment")
    public ResponseEntity<List<AddCommentResponse>> newComment(@RequestBody AddCommentRequest dto,
                                                               @AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok()
                .body(articleService.newComment(dto, principal));
    }

    @PostMapping("/api/comment/like")
    public ResponseEntity<PressLikeOrBadResponse> pressLike(@RequestBody PressLikeOrBadRequest dto,
                                                            @AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok()
                .body(articleService.pressLikeOrBad(dto, principal));
    }

    @PostMapping("/api/comment/bad")
    public ResponseEntity<PressLikeOrBadResponse> pressBad(@RequestBody PressLikeOrBadRequest dto,
                                                           @AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok()
                .body(articleService.pressLikeOrBad(dto, principal));
    }
}
