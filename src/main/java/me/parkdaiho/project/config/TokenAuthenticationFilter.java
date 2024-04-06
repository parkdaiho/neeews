package me.parkdaiho.project.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.service.user.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = tokenService.getAccessTokenByRequest(request);
        if (accessToken != null) {
            setAuthentication(request, accessToken);
        }

        String refreshToken = tokenService.getRefreshTokenByRequest(request);
        if (accessToken == null && refreshToken != null) {
            doFilterInternal(request, response, filterChain, refreshToken);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain,
                                  String refreshToken) throws ServletException, IOException {
        String accessToken = tokenService.getAccessTokenByRefreshToken(refreshToken);
        request.setAttribute("token", accessToken);

        setAuthentication(request, accessToken);

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(HttpServletRequest request, String accessToken) {
        Authentication authentication = tokenService.getAuthenticationByAccessToken(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String nickname = tokenService.getNicknameByAccessToken(accessToken);
        request.setAttribute("nickname", nickname);
    }
}
