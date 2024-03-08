package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.article.ArticleCommentViewResponse;
import me.parkdaiho.project.dto.article.ArticleViewResponse;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.dto.article.SearchNaverNewsResponse;
import me.parkdaiho.project.service.article.ArticleCommentService;
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
    private final ArticleCommentService articleCommentService;

    private final int PAGES_PER_PAGE_BLOCK = 5;

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
        Page<ArticleCommentViewResponse> comments = articleCommentService.getDefaultArticleComments(id);

        getCommentInfoModel(comments, model);

        model.addAttribute("article", article);
        model.addAttribute("sort", "date");

        return "article";
    }

    @GetMapping("/articles/{id}/comments")
    public String articleCommentView(@PathVariable Long id,
                                     @RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false, defaultValue = "date") String sort,
                                     Model model) {
        Page<ArticleCommentViewResponse> comments = articleCommentService.getArticleCommentView(page, sort, id);

        getCommentInfoModel(comments, model);

        model.addAttribute("sort", sort);

        return "comments-area";
    }

    private void getCommentInfoModel(Page<ArticleCommentViewResponse> comments, Model model) {
        int page = comments.getNumber() + 1;
        int totalPages = comments.getTotalPages();
        int firstNumOfPageBlock = page / PAGES_PER_PAGE_BLOCK * PAGES_PER_PAGE_BLOCK + 1;
        int lastNumOfPageBlock = firstNumOfPageBlock + PAGES_PER_PAGE_BLOCK - 1;
        if(totalPages < lastNumOfPageBlock) {
            lastNumOfPageBlock = totalPages;
        }

        int nextPage = comments.hasNext() ? page + 1 : totalPages;
        int previousPage = comments.hasPrevious() ? page - 1 : page;

        model.addAttribute("totalElements", comments.getTotalElements());
        model.addAttribute("articleId", "id");
        model.addAttribute("page", page);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstNumOfPageBlock", firstNumOfPageBlock);
        model.addAttribute("lastNumOfPageBlock", lastNumOfPageBlock);
        model.addAttribute("comments", comments.getContent());
    }
}

