package me.parkdaiho.project.repository.user;

import me.parkdaiho.project.domain.user.RefreshToken;
import me.parkdaiho.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken (String refreshToken);
}
