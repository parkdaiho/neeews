package me.parkdaiho.project.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.properties.CookieProperties;
import me.parkdaiho.project.dto.user.MembershipSearchRequest;
import me.parkdaiho.project.dto.user.MembershipViewResponse;
import me.parkdaiho.project.service.CommentService;
import me.parkdaiho.project.service.PostService;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class UserViewController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    private final CookieProperties cookieProperties;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        String username = userService.getSavedUsername(request);

        model.addAttribute("username", username);

        return "login";
    }

    @GetMapping("/login/{path}")
    public void oauthLogin(@PathVariable String path, HttpServletResponse response) throws IOException {
        String url = "/oauth2/authorization/" + path;

        response.sendRedirect(url);
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "sign-up";
    }

    @GetMapping("/find-user-info")
    public String findUserInfo() {
        return "find-user-info";
    }

    @GetMapping("/find-username")
    public String findUsername(HttpServletRequest request, HttpServletResponse response) {
        userService.deleteExistingCookieForFindUserInfo(request, response);

        return "find-username";
    }

    @GetMapping("/find-username/result")
    public String foundUsername(HttpServletRequest request, HttpServletResponse response,
                                Model model) {
        String username = userService.getFoundUsername(request, response);

        model.addAttribute("username", username);

        return "find-user-info-result";
    }

    @GetMapping("/find-password")
    public String findPassword(HttpServletRequest request, HttpServletResponse response) {
        userService.deleteExistingCookieForFindUserInfo(request, response);

        return "find-password";
    }

    @GetMapping("/change-password")
    public String changePassword(HttpServletRequest request) {
        userService.checkCookieForPage(request, cookieProperties.getEmailInFindPasswordName());

        return "change-password";
    }

    @GetMapping("/find-password/result")
    public String findPasswordResult() {
        return "find-user-info-result";
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
