package me.parkdaiho.project.repository.article;

import me.parkdaiho.project.domain.article.LikeOrBad;
import me.parkdaiho.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeOrBadRepository extends JpaRepository<LikeOrBad, Long> {

    Optional<LikeOrBad> findByUser(User user);
}
