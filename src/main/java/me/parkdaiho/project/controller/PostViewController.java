package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.board.PostViewResponse;
import me.parkdaiho.project.service.board.PostService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@RequiredArgsConstructor
@Controller
public class PostViewController {

    private final PostService postService;

    @GetMapping("/posts")
    public String posts() {
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String postView(@PathVariable Long id, Model model) {
        PostViewResponse post = postService.getPostViewResponse(id);

        model.addAttribute("post", post);

        return "post";
    }

    @GetMapping("/new-post")
    public String newPost() {
        return "post-write";
    }

}
