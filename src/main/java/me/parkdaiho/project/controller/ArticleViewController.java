package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ArticleViewController {

    private final ArticleService articleService;

    @GetMapping("/new-article")
    public String newArticle() {
        return "test";
    }

    @GetMapping("/articles/{id}")
    public String articleView(@PathVariable Long id, Model model) {
        Map<String, Object> articleView = articleService.getArticleView(id);

        model.addAttribute("article", articleView.get("article"));
        model.addAttribute("comments", articleView.get("comments"));

        return "article";
    }
}
