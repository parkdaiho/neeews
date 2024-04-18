package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.post.*;
import me.parkdaiho.project.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<Void> newPost(AddPostRequest request, @AuthenticationPrincipal PrincipalDetails principal) throws IOException {
        Long savedPostId = postService.getSavedPostId(request, principal);

        return ResponseEntity.created(URI.create("/posts/" + savedPostId)).build();
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal == null) throw new IllegalArgumentException("Unexpected access");

        postService.deletePost(id, principal);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/posts/{id}")
    public ResponseEntity<Void> modifyPost(@PathVariable Long id, ModifyPostRequest request,
                                           @AuthenticationPrincipal PrincipalDetails principal) throws IOException {
        if(principal == null) throw new IllegalArgumentException("Unexpected access");

        Long modifiedPostId = postService.getModifiedPostId(id, request, principal);

        return ResponseEntity.ok()
                .header("Location", "/posts/" + modifiedPostId)
                .build();
    }
}