package me.parkdaiho.project.config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.oauth2.OAuth2AuthenticationCustomException;
import me.parkdaiho.project.config.oauth2.OAuth2UserInfo;
import me.parkdaiho.project.domain.user.Role;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationCustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final UserService userService;
    private final AuthenticationCustomSuccessHandler authenticationCustomSuccessHandler;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username") == null ? null : request.getParameter("username");
        String redirectUrl = null;

        if(username == null) {
            redirectUrl = getOAuth2SignUpRedirectUrl(request, (OAuth2AuthenticationCustomException) exception);
        } else {
            User user = userService.findByUsername(username);
            redirectUrl = getRedirectUrl(request, response, user);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(redirectUrl);
        dispatcher.forward(request, response);
    }

    private String getRedirectUrl(HttpServletRequest request, HttpServletResponse response,
                                  User user) throws IOException {
        if(user.getRole().equals(Role.ADMIN)) {
            return authenticationCustomSuccessHandler.getLoginSuccessUrl(request, response, user);
        } else {
            return "/login?error=unexpected-user";
        }
    }

    private String getOAuth2SignUpRedirectUrl(HttpServletRequest request, OAuth2AuthenticationCustomException exception) {
        OAuth2UserInfo oAuth2UserInfo = exception.getoAuth2UserInfo();

        request.setAttribute("email", oAuth2UserInfo.getEmail());
        request.setAttribute("provider", oAuth2UserInfo.getProvider());

        return "/sign-up";
    }
}
