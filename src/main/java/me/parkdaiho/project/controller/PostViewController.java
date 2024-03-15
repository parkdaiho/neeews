package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.CommentProperties;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.dto.board.PostViewResponse;
import me.parkdaiho.project.dto.comment.CommentViewResponse;
import me.parkdaiho.project.service.CommentService;
import me.parkdaiho.project.service.board.PostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class PostViewController {

    private final PostService postService;
    private final CommentService commentService;

    private final CommentProperties commentProperties;

    @GetMapping("/posts")
    public String posts() {
        return "posts";
    }

    @GetMapping("/new-post")
    public String newPost() {
        return "post-write";
    }

    @GetMapping("/posts/{id}")
    public String postView(@PathVariable Long id, Model model) {
        PostViewResponse post = postService.getPostViewResponse(id);
        Page<CommentViewResponse> comments = commentService.getDefaultComments(id, Domain.POST);

        model.addAttribute("post", post);
        model.addAttribute("sort", "date");

        addCommentInfoToModel(comments, model);

        return "post";
    }

    @GetMapping("/posts/{id}/comments")
    public String postCommentView(@PathVariable Long id,
                                     @RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false, defaultValue = "date") String sort,
                                     Model model) {
        Page<CommentViewResponse> comments = commentService.getCommentView(page, sort, id, Domain.POST);

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
