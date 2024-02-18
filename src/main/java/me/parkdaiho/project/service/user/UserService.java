package me.parkdaiho.project.service.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.SignUpRequest;
import me.parkdaiho.project.repository.UserRepository;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User signUp(SignUpRequest request) {
        return userRepository.save(request.toEntity());
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "refresh_token");
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
