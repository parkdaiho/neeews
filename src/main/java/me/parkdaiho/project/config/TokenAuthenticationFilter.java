package me.parkdaiho.project.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.properties.JwtProperties;
import me.parkdaiho.project.config.token.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader(jwtProperties.getAuthorizationHeaderName());
        String accessToken = getAccessToken(authorization);

        if(tokenProvider.validToken(accessToken)) {
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String nickname = tokenProvider.getUserNickname(accessToken);
            request.setAttribute("nickname", nickname);
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorization) {
        if(authorization != null && authorization.startsWith(jwtProperties.getAuthorizationTokenPrefix())) {
            return authorization.substring(jwtProperties.getAuthorizationTokenPrefix().length());
        }

        return null;
    }
}
