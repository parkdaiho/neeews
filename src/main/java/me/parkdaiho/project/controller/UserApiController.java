package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.user.OAuth2SignUpRequest;
import me.parkdaiho.project.dto.user.SignUpRequest;
import me.parkdaiho.project.dto.user.SignUpResponse;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(userService.signUp(request));
    }

    @PostMapping("/oauth2/sign-up")
    public ResponseEntity<SignUpResponse> oAuth2SignUp(@Valid @RequestBody OAuth2SignUpRequest request) {
        return ResponseEntity.ok(userService.signUp(request));
    }
}
