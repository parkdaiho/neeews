package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.CommentProperties;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.Sort;
import me.parkdaiho.project.dto.comment.CommentViewResponse;
import me.parkdaiho.project.dto.article.ArticleViewResponse;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import me.parkdaiho.project.service.CommentService;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ArticleViewController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @PostMapping("/articles")
    public String articles(SearchNaverNewsRequest request, Model model) {
        SearchNaverNewsResponse response = articleService.getSearchResult(request);

        model.addAttribute("items", response.getItems());

        return "articles";
    }

    @GetMapping("/articles/{id}")
    public String articleView(@PathVariable Long id,
                              HttpServletRequest request, HttpServletResponse response,
                              Model model) {
        ArticleViewResponse article = articleService.getArticleView(id, request, response);
        Page<CommentViewResponse> comments = commentService.getDefaultComments(id, Domain.ARTICLE);

        model.addAttribute("article", article);
        model.addAttribute("domain", Domain.ARTICLE.getDomainPl());
        model.addAttribute("sort", Sort.LATEST.getValue());

        commentService.addCommentInfoToModel(comments, model);

        return "article";
    }

    @GetMapping("/articles/{id}/comments")
    public String articleCommentView(@PathVariable Long id,
                                     int page, String sort,
                                     Model model) {
        Page<CommentViewResponse> comments = commentService.getCommentView(page, Sort.valueOf(sort.toUpperCase()), id, Domain.ARTICLE);

        model.addAttribute("sort", sort);

        commentService.addCommentInfoToModel(comments, model);

        return "comments-area";
    }

}

