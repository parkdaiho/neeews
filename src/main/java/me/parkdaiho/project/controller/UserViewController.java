package me.parkdaiho.project.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.Sort;
import me.parkdaiho.project.dto.comment.MyCommentResponse;
import me.parkdaiho.project.dto.post.MyPostResponse;
import me.parkdaiho.project.dto.user.MembershipSearchRequest;
import me.parkdaiho.project.dto.user.MembershipViewResponse;
import me.parkdaiho.project.service.CommentService;
import me.parkdaiho.project.service.PostService;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserViewController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "sign-up";
    }

    @GetMapping("/my-page")
    public String myPage(@AuthenticationPrincipal PrincipalDetails principal,
                         Model model) {
        model.addAttribute("isUser", userService.isUser(principal));
        userService.addAttributesForMyPage(principal, model);

        return "my-page";
    }

    @GetMapping("/my-page/information")
    public String informationPage(@AuthenticationPrincipal PrincipalDetails principal,
                                  Model model) {
        userService.addAttributesForMyPage(principal, model);

        return "member-info";
    }

    @GetMapping("/my-page/withdrawal/{id}")
    public String withdrawalPage(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);

        return "withdrawal";
    }

    @GetMapping("/my-page/membership")
    public String memberShipPage(MembershipSearchRequest request,
                                 @AuthenticationPrincipal PrincipalDetails principal,
                                 Model model) {
        Page<MembershipViewResponse> users = userService.getUsers(request, principal);

        userService.addAttributesForMembership(users, model);

        model.addAttribute("order", request.getSort()); // for pagination
        
        model.addAttribute("sort", request.getSort());
        model.addAttribute("searchSort", request.getSearchSort());
        model.addAttribute("query", request.getQuery());

        return "membership";
    }

    @GetMapping("/my-page/my-posts")
    public String myPostsPage(@Nullable Integer page,
                              @AuthenticationPrincipal PrincipalDetails principal,
                              Model model) {
        postService.getMyPostsToModel(page, principal, model);

        return "my-posts";
    }

    @GetMapping("/my-page/my-comments")
    public String myCommentsPage(@Nullable Integer page,
                                 @AuthenticationPrincipal PrincipalDetails principal,
                                 Model model) {
        commentService.getMyCommentsToModel(page, principal, model);

        return "my-comments";
    }
}
