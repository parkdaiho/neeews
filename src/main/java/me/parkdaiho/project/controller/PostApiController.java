package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.board.AddPostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    @PostMapping("/api/new-post")
    public ResponseEntity<?> newPost(AddPostRequest request) {

        System.out.println("title: " + request.getTitle());
        System.out.println("contents: " + request.getContents());

        for(MultipartFile file : request.getFiles()) {
            System.out.println(file.getOriginalFilename());
        }

        return ResponseEntity.ok().build();
    }
}