package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.board.AddPostRequest;
import me.parkdaiho.project.service.board.PostService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
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

        return ResponseEntity.ok()
                .header("Location", "/posts")
                .build();
    }
}