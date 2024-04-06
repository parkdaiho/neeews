package me.parkdaiho.project.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.oauth2.OAuth2AuthorizationRequestRepositoryBasedOnCookie;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.service.user.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationCustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final OAuth2AuthorizationRequestRepositoryBasedOnCookie oAuth2AuthorizationRequestRepositoryBasedOnCookie;
    private final TokenService tokenService;

    private final static String REDIRECT_PATH = "/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        clearAuthenticationAttributes(request, response);

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();

        String loginSuccessUrl = getLoginSuccessUrl(request, response, user);

        getRedirectStrategy().sendRedirect(request, response, loginSuccessUrl);
    }

    public String getLoginSuccessUrl(HttpServletRequest request, HttpServletResponse response,
                                  User user) {
        String savedRefreshToken = tokenService.saveRefreshToken(user);
        tokenService.addRefreshTokenToCookie(request, response, savedRefreshToken);

        String accessToken = tokenService.getAccessToken(user);

        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)
                .queryParam("token", accessToken)
                .build()
                .toUriString();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        oAuth2AuthorizationRequestRepositoryBasedOnCookie.removeAuthorizationRequestCookie(request, response);
    }
}
