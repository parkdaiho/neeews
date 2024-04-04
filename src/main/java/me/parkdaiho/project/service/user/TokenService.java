package me.parkdaiho.project.service.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.JwtProperties;
import me.parkdaiho.project.config.token.TokenProvider;
import me.parkdaiho.project.domain.user.RefreshToken;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.repository.user.RefreshTokenRepository;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    private final JwtProperties jwtProperties;

    public String createNewAccessToken(String refreshToken) {
        if(!checkInvalidRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("Non-validated refreshToken");
        }

        User user = userService.findById(tokenProvider.getUserId(refreshToken));

        return tokenProvider.generateToken(user, jwtProperties.getAccessTokenDuration());
    }

    public Boolean checkInvalidRefreshToken(String refreshToken) {
        if(!tokenProvider.validToken(refreshToken)) return false;

        Long id = tokenProvider.getUserId(refreshToken);

        RefreshToken savedRefreshToken = refreshTokenRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected refreshTokenId: " + id));

        return refreshToken.equals(savedRefreshToken.getRefreshToken());
    }

    public void addHeaderAttribute(HttpServletRequest request, Model model) {
        Cookie refreshTokenCookie = CookieUtils.getCookieByName(request, jwtProperties.getRefreshTokenCookieName());
        if(refreshTokenCookie == null) return;

        String refreshToken = refreshTokenCookie.getValue();
        String username = tokenProvider.getUserNickname(refreshToken);

        model.addAttribute("username", username);
    }
}
