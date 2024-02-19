package me.parkdaiho.project.domain.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.token.TokenProvider;
import me.parkdaiho.project.repository.RefreshTokenRepository;
import me.parkdaiho.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RefreshTokenTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    @DisplayName("refresh_token create test")
    public void createRefreshToken() {
        // given
        User user = User.builder()
                .username("test")
                .password("test")
                .nickname("test")
                .email("test")
                .build();

        // when
        RefreshToken refreshToken = refreshTokenRepository.save(new RefreshToken(user));

        // then
        assertThat(refreshToken.getId()).isEqualTo(user.getId());
    }
}