package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.board.AddPostRequest;
import me.parkdaiho.project.service.board.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/new-post")
    public ResponseEntity<Void> newPost(AddPostRequest request, @AuthenticationPrincipal PrincipalDetails principal) {
        Long savedPostId = postService.getSavedPostId(request, principal);

        return ResponseEntity.created(URI.create("/posts/" + savedPostId)).build();
    }
}