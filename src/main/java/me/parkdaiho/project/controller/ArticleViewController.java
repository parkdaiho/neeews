package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.article.ArticleViewResponse;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ArticleViewController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public String articles(String query, String sort, Model model) {
        SearchNaverNewsRequest dto = new SearchNaverNewsRequest();
        dto.setQuery(query);
        dto.setSort(sort);

        SearchNaverNewsResponse response = articleService.getSearchResult(dto);

        model.addAttribute("items", response.getItems());

        return "articles";
    }

    @GetMapping("/articles/{id}")
    public String articleView(@PathVariable Long id, Model model) {
        ArticleViewResponse article = articleService.getArticleViewById(id);

        model.addAttribute("article", article);

        return "article";
    }
}
