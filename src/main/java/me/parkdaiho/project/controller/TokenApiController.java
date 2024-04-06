package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.CreateAccessTokenRequest;
import me.parkdaiho.project.dto.CreateAccessTokenResponse;
import me.parkdaiho.project.service.user.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createAccessToken(@RequestBody CreateAccessTokenRequest request) {
        String accessToken = tokenService.getAccessTokenByRefreshToken(request.getRefreshToken());

        return ResponseEntity.ok(new CreateAccessTokenResponse(accessToken));
    }
}
