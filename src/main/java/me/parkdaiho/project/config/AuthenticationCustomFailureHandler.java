package me.parkdaiho.project.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
            redirectUrl = "/login?error=oauth2-authorization-fail";
        } else {
            User user = userService.findByUsername(username);
            if(user.getRole().equals(Role.ADMIN)) {
                redirectUrl = authenticationCustomSuccessHandler.getLoginSuccessUrl(request, response, user);
            } else {
                redirectUrl = "/login?error=unexpected-user";
            }
        }

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
