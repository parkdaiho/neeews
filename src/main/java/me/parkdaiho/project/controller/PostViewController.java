package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.Order;
import me.parkdaiho.project.dto.post.ModifyViewResponse;
import me.parkdaiho.project.dto.post.PostListViewResponse;
import me.parkdaiho.project.dto.post.PostViewResponse;
import me.parkdaiho.project.dto.post.SearchPostRequest;
import me.parkdaiho.project.dto.comment.CommentViewResponse;
import me.parkdaiho.project.service.CommentService;
import me.parkdaiho.project.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PostViewController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/posts")
    public String posts(SearchPostRequest request, Model model) {
        if (request.getPage() == null) request.setPage(1);
        if (request.getSort() == null) request.setSort(Order.LATEST.getValue());

        Page<PostListViewResponse> posts = postService.getPostListViewResponse(request);

        postService.addPostsInfoToModel(posts, model);

        return "posts";
    }

    @GetMapping("/new-post")
    public String newPost() {
        return "new-post";
    }

    @GetMapping("/posts/{id}")
    public String postView(@PathVariable Long id,
                           HttpServletRequest request, HttpServletResponse response,
                           Model model) {
        PostViewResponse post = postService.getPostViewResponse(id, request, response);
        Page<CommentViewResponse> comments = commentService.getDefaultComments(id, Domain.POST);

        model.addAttribute("post", post);
        model.addAttribute("domain", Domain.POST.getDomainPl());
        model.addAttribute("sort", Order.LATEST.getValue());

        commentService.addCommentInfoToModel(comments, model);

        return "post";
    }

    @GetMapping("/posts/{id}/comments")
    public String postCommentView(@PathVariable Long id,
                                  int page, String sort,
                                  Model model) {
        Page<CommentViewResponse> comments = commentService.getCommentView(page, Order.valueOf(sort.toUpperCase()), id, Domain.POST);

        model.addAttribute("sort", sort);

        commentService.addCommentInfoToModel(comments, model);

        return "comments-area";
    }

    @GetMapping("/post")
    public String modifyPage(Long id, Model model) {
        ModifyViewResponse post = postService.getModifyViewResponse(id);

        model.addAttribute("post", post);

        return "post-write";
    }
}
