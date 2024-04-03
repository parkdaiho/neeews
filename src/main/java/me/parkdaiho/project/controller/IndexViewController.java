package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.Order;
import me.parkdaiho.project.dto.IndexViewResponse;
import me.parkdaiho.project.service.article.ArticleService;
import me.parkdaiho.project.service.PostService;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

    @GetMapping("/items")
    public String getItems (String domain, String order,
                            Model model) {
        Domain domainEnum = Domain.valueOf(domain.toUpperCase());
        Order orderEnum = Order.valueOf(order.toUpperCase());

        List<IndexViewResponse> items;
        switch (domainEnum) {
            case ARTICLE -> items = articleService.getArticlesForIndex(orderEnum);
            case POST -> items = postService.getPostsForIndex(orderEnum);

            default -> throw new IllegalArgumentException("Unexpected domain: " + domain);
        }

        model.addAttribute("domain", domain);
        model.addAttribute("order", order);
        model.addAttribute("items", items);

        return "index-middle-part";
    }
}
