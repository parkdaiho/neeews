package me.parkdaiho.project.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserViewController {

    @GetMapping("/login")
    public String loginPage(@Nullable @AuthenticationPrincipal PrincipalDetails principal, Model model) {
        if(principal != null) {
            model.addAttribute("nickname", principal.getUser().getNickname());
        }

        return "login";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "sign-up";
    }
}
