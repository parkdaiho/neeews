package me.parkdaiho.project.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
