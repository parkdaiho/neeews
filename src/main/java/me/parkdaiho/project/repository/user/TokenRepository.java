package me.parkdaiho.project.repository.user;

import me.parkdaiho.project.domain.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByRefreshToken (String refreshToken);
    Optional<Token> findByAccessToken(String accessToken);
}
