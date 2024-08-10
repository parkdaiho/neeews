package me.parkdaiho.project.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.JwtProperties;
import me.parkdaiho.project.domain.user.Token;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.service.user.TokenService;
import me.parkdaiho.project.util.CookieUtils;
import me.parkdaiho.project.util.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;


@RequiredArgsConstructor
public class LogoutCustomHandler implements LogoutHandler {

    private final JwtProperties jwtProperties;
    private final TokenService tokenService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logout(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtils.getCookieByName(request, jwtProperties.getRefreshTokenCookieName());
        if (cookie == null) throw new IllegalArgumentException("Unexpected access");

        String refreshToken = cookie.getValue();
        tokenService.invalidateToken(refreshToken);

        CookieUtils.deleteCookie(request, response, jwtProperties.getRefreshTokenCookieName());
    }
}
