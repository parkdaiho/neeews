package me.parkdaiho.project.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.user.Role;
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
        String refreshToken = tokenService.getRefreshTokenByRequest(request);

        Authentication authentication = null;

        if (accessToken != null) {
            authentication = getAuthenticationByAccessToken(request, accessToken);
        } else if (refreshToken != null) {
            authentication = getAuthenticationByRefreshToken(request, refreshToken);
        } else {
            request.setAttribute("login", false);
        }

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthenticationByRefreshToken(HttpServletRequest request, String refreshToken) throws ServletException, IOException {
        String accessToken = tokenService.getAccessTokenByRefreshToken(refreshToken);
        request.setAttribute("token", accessToken);

        return getAuthenticationByAccessToken(request, accessToken);
    }

    private Authentication getAuthenticationByAccessToken(HttpServletRequest request, String accessToken) {
        Authentication authentication = tokenService.getAuthenticationByAccessToken(accessToken);
        if (authentication == null) return null;

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String nickname = tokenService.getNicknameByAccessToken(accessToken);
        Role role = tokenService.getRoleByAccessToken(accessToken);

//        request.setAttribute("nickname", nickname);
        request.setAttribute("login", true);
        request.setAttribute("isManager", !role.getIsUser());
        request.setAttribute("isAdmin", role == Role.ADMIN);

        return authentication;
    }
}
