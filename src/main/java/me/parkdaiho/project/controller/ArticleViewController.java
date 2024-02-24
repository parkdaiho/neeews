package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.article.ArticleListResponse;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ArticleViewController {

    private final ArticleService articleService;

    private final int SIZE = 17;
    private final int BLOCK_SIZE = 8;

    @GetMapping("/articles")
    public String articleList(@RequestParam(defaultValue = "1", required = false) int page, Model model) {
        Page<ArticleListResponse> articles = articleService.getArticleList(page, SIZE);

        int totalPages = articles.getTotalPages();

        int blockNum = page / BLOCK_SIZE;
        int firstPageOfPageBlock = blockNum * BLOCK_SIZE + 1;
        int lastPageOfPageBlock = (blockNum + 1) * BLOCK_SIZE;
        if(lastPageOfPageBlock > totalPages) lastPageOfPageBlock = totalPages;

        model.addAttribute("articles", articles);
        model.addAttribute("totalElements", articles.getTotalElements());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstPageOfPageBlock", firstPageOfPageBlock);
        model.addAttribute("lastPageOfPageBlock", lastPageOfPageBlock);

        return "articlse";
    }

    @GetMapping("/articles/{id}")
    public String articleView(@PathVariable Long id, Model model) {
        Map<String, Object> articleView = articleService.getArticleView(id);

        model.addAttribute("article", articleView.get("article"));
        model.addAttribute("comments", articleView.get("comments"));

        return "article";
    }
}
