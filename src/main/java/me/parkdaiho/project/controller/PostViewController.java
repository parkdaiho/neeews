package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class PostViewController {

    @GetMapping("/posts")
    public String posts() {
        return "posts";
    }

    @GetMapping("/new-post")
    public String newPost() {
        return "post-write";
    }
}
