package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.Order;
import me.parkdaiho.project.service.article.ArticleService;
import me.parkdaiho.project.service.PostService;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexViewController {

    private final UserService userService;
    private final ArticleService articleService;
    private final PostService postService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        String userNickname = userService.findUserNicknameByRefreshToken(request);
        if(userNickname != null) {
            model.addAttribute("userNickname", userNickname);
        }

        Order order = Order.POPULARITY;

        model.addAttribute("articles", articleService.getArticlesForIndex(order));
        model.addAttribute("posts", postService.getPostsForIndex(order));

        return "index";
    }
}
