package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.article.ArticleViewResponse;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ArticleViewController {

    private final ArticleService articleService;

    @PostMapping("/articles/{id}")
    public String articleView(@PathVariable Long id, Model model) {
        ArticleViewResponse articleView = articleService.getArticleViewResponse(id);

        model.addAttribute("article", articleView);

        return "article";
    }
}
