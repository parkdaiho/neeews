package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.dto.comment.AddReplyRequest;
import me.parkdaiho.project.dto.PollRequest;
import me.parkdaiho.project.dto.comment.AddCommentRequest;
import me.parkdaiho.project.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<Void> addComment(@RequestBody AddCommentRequest request,
                                           @AuthenticationPrincipal PrincipalDetails principal) throws URISyntaxException {
        Domain domain = Domain.getDomainByDomainPl(request.getDomain());

        commentService.addComment(request, principal, domain);

        return ResponseEntity.created(URI.create("/" + domain.getDomainPl() + "/" + request.getId())).build();
    }

    @PostMapping("/api/reply")
    public ResponseEntity<Void> addReply(@RequestBody AddReplyRequest request,
                                         @AuthenticationPrincipal PrincipalDetails principal) {
        commentService.addReply(request, principal);

        return ResponseEntity.ok().build();
    }
}
