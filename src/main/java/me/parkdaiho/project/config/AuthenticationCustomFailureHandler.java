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
import org.springframework.cglib.proxy.Dispatcher;
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

        if(username == null) {
            oAuth2SignUpRedirect(request, response, (OAuth2AuthenticationCustomException) exception);
        } else {
            User user = userService.findByUsername(username);
            formLoginRedirect(request, response, user);
        }
    }

    private void formLoginRedirect(HttpServletRequest request, HttpServletResponse response,
                                  User user) throws IOException {
        String redirectUrl = null;

        if(user.getRole().equals(Role.ADMIN)) {
            redirectUrl = authenticationCustomSuccessHandler.getLoginSuccessUrl(request, response, user);
        } else {
            redirectUrl = "/login?error=unexpected-user";
        }

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    private void oAuth2SignUpRedirect(HttpServletRequest request, HttpServletResponse response,
                                      OAuth2AuthenticationCustomException exception) throws ServletException, IOException {
        OAuth2UserInfo oAuth2UserInfo = exception.getoAuth2UserInfo();

        request.setAttribute("email", oAuth2UserInfo.getEmail());
        request.setAttribute("provider", oAuth2UserInfo.getProvider());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/sign-up");
        dispatcher.forward(request, response);
    }
}
