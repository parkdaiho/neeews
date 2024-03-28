package me.parkdaiho.project.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.oauth2.OAuth2AuthorizationRequestRepositoryBasedOnCookie;
import me.parkdaiho.project.config.properties.JwtProperties;
import me.parkdaiho.project.domain.user.RefreshToken;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.repository.user.RefreshTokenRepository;
import me.parkdaiho.project.config.token.TokenProvider;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationCustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuth2AuthorizationRequestRepositoryBasedOnCookie oAuth2AuthorizationRequestRepositoryBasedOnCookie;

    private final JwtProperties jwtProperties;
    private final static String REDIRECT_PATH = "/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        clearAuthenticationAttributes(request, response);

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();

        String refreshToken = tokenProvider.generateToken(user, jwtProperties.getRefreshTokenDuration());
        saveRefreshToken(user, refreshToken);
        addRefreshToken(request, response, refreshToken);

        String accessToken = tokenProvider.generateToken(user, jwtProperties.getAccessTokenDuration());
        String loginSuccessUrl = loginSuccessUrl(accessToken);

        getRedirectStrategy().sendRedirect(request, response, loginSuccessUrl);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        oAuth2AuthorizationRequestRepositoryBasedOnCookie.removeAuthorizationRequestCookie(request, response);
    }

    private String loginSuccessUrl(String accessToken) {
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)
                .queryParam("token", accessToken)
                .build()
                .toUriString();
    }

    private void addRefreshToken(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        CookieUtils.deleteCookie(request, response, jwtProperties.getRefreshTokenCookieName());
        CookieUtils.addCookie(response, jwtProperties.getRefreshTokenCookieName(),
                refreshToken, (int) jwtProperties.getRefreshTokenDuration().toSeconds());
    }

    private void saveRefreshToken(User user, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findById(user.getId())
                .map(entity -> entity.update(newRefreshToken))
                .orElseGet(() -> refreshTokenRepository.save(
                        RefreshToken.builder()
                                .user(user)
                                .refreshToken(newRefreshToken)
                                .build())
                );

        refreshTokenRepository.save(refreshToken);
    }
}
