package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.article.*;
import me.parkdaiho.project.service.article.ArticleCommentService;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;
    private final ArticleCommentService articleCommentService;

    @PostMapping("/api/article")
    public ResponseEntity<Void> getArticle(@RequestBody ArticleViewRequest dto) throws IOException {
        Long articleId = articleService.getArticleId(dto);

        return ResponseEntity.created(URI.create("/articles/" + articleId)).build();
    }

    @PostMapping("/api/article-comment")
    public ResponseEntity<List<ArticleCommentViewResponse>> addArticleComment(@RequestBody AddArticleCommentRequest request,
                                                                              @AuthenticationPrincipal PrincipalDetails principal) {
        List<ArticleCommentViewResponse> comments = articleCommentService.addArticleComment(request, principal);

        return ResponseEntity.ok(comments);
    }

    @PostMapping("/api/reply")
    public ResponseEntity<List<ArticleCommentViewResponse>> addReply(@RequestBody AddReplyRequest request,
                                                                     @AuthenticationPrincipal PrincipalDetails principal) {
        List<ArticleCommentViewResponse> comments = articleCommentService.addReply(request, principal);

        return ResponseEntity.ok(comments);
    }

    @PutMapping("/api/article-comment/recommendation")
    public ResponseEntity<Void> setGoodOrBad(@RequestBody SetGoodOrBadRequest request,
                                             @AuthenticationPrincipal PrincipalDetails principal) {
        articleCommentService.setGoodOrBad(request, principal);

        return ResponseEntity.ok().build();
    }
}