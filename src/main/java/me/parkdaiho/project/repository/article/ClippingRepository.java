package me.parkdaiho.project.repository.article;

import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.Clipping;
import me.parkdaiho.project.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClippingRepository extends JpaRepository<Clipping, Long> {

    Page<Clipping> findClippingByUser(User user, Pageable pageable);
    Optional<Clipping> findClippingByArticleAndUser(Article article, User user);
}
