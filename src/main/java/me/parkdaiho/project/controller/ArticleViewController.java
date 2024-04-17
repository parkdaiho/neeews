package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.Order;
import me.parkdaiho.project.domain.Sort;
import me.parkdaiho.project.dto.article.SearchedArticlesRequest;
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

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ArticleViewController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("/searched-articles")
    public String searchedArticles(SearchedArticlesRequest request, Model model) {
        SearchNaverNewsResponse response = articleService.getSearchNewsResult(request);

        articleService.addSearchedNewsResponseToModel(response, model);

        model.addAttribute("sort", request.getSort());
        model.addAttribute("query", request.getQuery());

        return "searched-articles";
    }

    @GetMapping("/articles")
    public String articles(String order, Model model) {
        if(order == null) order = Order.VIEWS.getValue();
        Order orderEnum = Order.valueOf(order.toUpperCase());

        model.addAttribute("articles", articleService.getArticlesForArticles(orderEnum));
        model.addAttribute("order", order);

        return "articles";
    }

    @GetMapping("/articles/{id}")
    public String articleView(@PathVariable Long id,
                              HttpServletRequest request, HttpServletResponse response,
                              Model model) {
        ArticleViewResponse article = articleService.getArticleView(id, request, response);
        Page<CommentViewResponse> comments = commentService.getDefaultComments(id, Domain.ARTICLE);

        articleService.addArticleToModel(article, model);

        model.addAttribute("domain", Domain.ARTICLE.getDomainPl());
        model.addAttribute("sort", Order.LATEST.getValue());

        commentService.addCommentInfoToModel(comments, model);

        return "article";
    }

    @GetMapping("/articles/{id}/comments")
    public String articleCommentView(@PathVariable Long id,
                                     int page, String order,
                                     Model model) {
        Page<CommentViewResponse> comments = commentService.getCommentView(page, Order.valueOf(order.toUpperCase()), id, Domain.ARTICLE);

        model.addAttribute("sort", order);

        commentService.addCommentInfoToModel(comments, model);

        return "comments-area";
    }

}

