package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.CommentProperties;
import me.parkdaiho.project.domain.Domain;
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
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ArticleViewController {

    private final ArticleService articleService;
    private final CommentService commentService;

    private final CommentProperties commentProperties;

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
    public String articleView(@PathVariable Long id,
                              Model model) {
        ArticleViewResponse article = articleService.getArticleView(id);
        Page<CommentViewResponse> comments = commentService.getDefaultComments(id, Domain.ARTICLE);

        model.addAttribute("article", article);
        model.addAttribute("sort", "date");

        addCommentInfoToModel(comments, model);

        return "article";
    }

    @GetMapping("/articles/{id}/comments")
    public String articleCommentView(@PathVariable Long id,
                                     @RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false, defaultValue = "date") String sort,
                                     Model model) {
        Page<CommentViewResponse> comments = commentService.getCommentView(page, sort, id, Domain.ARTICLE);

        addCommentInfoToModel(comments, model);

        model.addAttribute("sort", sort);

        return "comments-area";
    }

    private void addCommentInfoToModel(Page<CommentViewResponse> comments, Model model) {
        int page = comments.getNumber() + 1;
        int totalPages = comments.getTotalPages();
        int firstNumOfPageBlock = page / commentProperties.getPagesPerBlock() * commentProperties.getPagesPerBlock() + 1;
        int lastNumOfPageBlock = firstNumOfPageBlock + commentProperties.getPagesPerBlock() - 1;
        if(totalPages < lastNumOfPageBlock) {
            lastNumOfPageBlock = totalPages;
        }

        int nextPage = comments.hasNext() ? page + 1 : totalPages;
        int previousPage = comments.hasPrevious() ? page - 1 : page;

        model.addAttribute("totalElements", comments.getTotalElements());
        model.addAttribute("page", page);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstNumOfPageBlock", firstNumOfPageBlock);
        model.addAttribute("lastNumOfPageBlock", lastNumOfPageBlock);
        model.addAttribute("comments", comments.getContent());
    }
}

