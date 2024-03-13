package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PostViewController {

    @GetMapping("/posts")
    public String posts() {
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String postView(@PathVariable Long id) {

        return "post";
    }

    @GetMapping("/new-post")
    public String newPost() {
        return "post-write";
    }
}
