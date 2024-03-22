package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.Sort;
import me.parkdaiho.project.service.article.ArticleService;
import me.parkdaiho.project.service.board.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class IndexViewController {

    private final ArticleService articleService;
    private final PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        Sort sort = Sort.POPULARITY;

        model.addAttribute("articles", articleService.getArticlesForIndex(sort));
        model.addAttribute("posts", postService.getPostsForIndex(sort));

        return "index";
    }
}
