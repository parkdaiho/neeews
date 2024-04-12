package me.parkdaiho.project.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.oauth2.OAuth2AuthorizationRequestRepositoryBasedOnCookie;
import me.parkdaiho.project.domain.user.Token;
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

        Token token = issueToken(request, response, user);

        String loginSuccessUrl = getLoginSuccessUrl(token.getAccessToken());

        getRedirectStrategy().sendRedirect(request, response, loginSuccessUrl);
    }

    public Token issueToken(HttpServletRequest request, HttpServletResponse response,
                              User user) {
        Token issuedToken = tokenService.saveToken(user);
        tokenService.addRefreshTokenToCookie(request, response, issuedToken.getRefreshToken());

        return issuedToken;
    }

    public String getLoginSuccessUrl(String accessToken) {
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
