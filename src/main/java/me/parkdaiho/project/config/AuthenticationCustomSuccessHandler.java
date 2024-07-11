package me.parkdaiho.project.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.oauth2.OAuth2AuthorizationRequestRepositoryBasedOnCookie;
import me.parkdaiho.project.config.properties.CookieNameProperties;
import me.parkdaiho.project.domain.user.Token;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.service.user.TokenService;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationCustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final OAuth2AuthorizationRequestRepositoryBasedOnCookie oAuth2AuthorizationRequestRepositoryBasedOnCookie;
    private final TokenService tokenService;

    private final CookieNameProperties cookieNameProperties;

    private final static String REDIRECT_PATH = "/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        clearAuthenticationAttributes(request, response);

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        Token token = issueToken(request, response, principal.getUser());
        String loginSuccessUrl = getLoginSuccessUrl(token.getAccessToken());

        saveUsername(request, response, principal.getUsername());

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

    public void saveUsername(HttpServletRequest request, HttpServletResponse response,
                             String username) {
        boolean saveUsernameFlag = request.getParameter("save-username") != null;
        if(!saveUsernameFlag) {
            CookieUtils.deleteCookie(request, response, cookieNameProperties.getSavedUsername());

            return;
        }

        String serializedUsername = CookieUtils.serialize(username);
        Cookie cookie = CookieUtils.getCookieByName(request, cookieNameProperties.getSavedUsername());
        if(cookie == null) {
            cookie = new Cookie(cookieNameProperties.getSavedUsername(), serializedUsername);
        } else {
            cookie.setValue(serializedUsername);
        }

        response.addCookie(cookie);
    }
}
