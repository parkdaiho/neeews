package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ArticleViewController {

    @GetMapping("/new-article")
    public String newArticle() {
        return "test";
    }
}
