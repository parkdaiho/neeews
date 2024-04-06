package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.GetAccessTokenRequest;
import me.parkdaiho.project.dto.GetAccessTokenResponse;
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
    public ResponseEntity<GetAccessTokenResponse> createAccessToken(@RequestBody GetAccessTokenRequest request) {
        String accessToken = tokenService.getAccessTokenByRefreshToken(request.getRefreshToken());

        return ResponseEntity.ok(new GetAccessTokenResponse(accessToken));
    }
}
