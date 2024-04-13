package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.Sort;
import me.parkdaiho.project.domain.user.Role;
import me.parkdaiho.project.dto.user.UserInfoResponse;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class UserViewController {

    private final UserService userService;

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
    public String memberShipPage(@RequestParam(required = false, defaultValue = "1") int page,
                                 @RequestParam(required = false) String sort,
                                 @RequestParam(required = false) String searchSort,
                                 @RequestParam(required = false) String query,
                                 @AuthenticationPrincipal PrincipalDetails principal,
                                 Model model) {
        if(sort == null) sort = Sort.ALL.getProperty();

        System.out.println(sort);
        System.out.println(searchSort);
        System.out.println(query);
        userService.addAttributesForMembership(page, sort, searchSort, query, principal, model);

        return "membership";
    }
}
