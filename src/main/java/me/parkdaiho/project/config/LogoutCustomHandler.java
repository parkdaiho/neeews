package me.parkdaiho.project.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.JwtProperties;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;


@RequiredArgsConstructor
public class LogoutCustomHandler implements LogoutHandler {

    private final JwtProperties jwtProperties;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logout(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, jwtProperties.getRefreshTokenCookieName());
    }
}
