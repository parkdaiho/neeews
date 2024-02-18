package me.parkdaiho.project.config.oauth2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import java.time.Duration;

public class OAuth2AuthorizationRequestRepositoryBasedOnCookie
        implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_user_request";
    private final int OAUTH2_AUTHORIZATION_REQUEST_COOKIE_DURATION = 180;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return null;
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest,
                                            HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    public void removeAuthorizationRequestCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
    }
}
