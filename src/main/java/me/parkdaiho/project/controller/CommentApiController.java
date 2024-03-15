package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.dto.comment.AddReplyRequest;
import me.parkdaiho.project.dto.article.SetGoodOrBadRequest;
import me.parkdaiho.project.dto.comment.AddCommentRequest;
import me.parkdaiho.project.dto.comment.CommentViewResponse;
import me.parkdaiho.project.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;


    @PostMapping("/api/article-comment")
    public ResponseEntity<Void> addArticleComment(@RequestBody AddCommentRequest request,
                                                                       @AuthenticationPrincipal PrincipalDetails principal) throws URISyntaxException {
        commentService.addComment(request, principal, Domain.ARTICLE);

        return ResponseEntity.created(URI.create("/articles/" + request.getId())).build();
    }

    @PostMapping("/api/post-comment")
    public ResponseEntity<Void> addPostComment(@RequestBody AddCommentRequest request,
                                                                       @AuthenticationPrincipal PrincipalDetails principal) {
        commentService.addComment(request, principal, Domain.ARTICLE);

        return ResponseEntity.created(URI.create("/posts/" + request.getId())).build();
    }

    @PostMapping("/api/reply")
    public ResponseEntity<Void> addReply(@RequestBody AddReplyRequest request,
                                                              @AuthenticationPrincipal PrincipalDetails principal) {
        List<CommentViewResponse> comments = commentService.addReply(request, principal);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/comment")
    public ResponseEntity<Void> setGoodOrBad(@RequestBody SetGoodOrBadRequest request,
                                             @AuthenticationPrincipal PrincipalDetails principal) {
        commentService.setGoodOrBad(request, principal);

        return ResponseEntity.ok().build();
    }
}
