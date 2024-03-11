package me.parkdaiho.project.repository.article;

import me.parkdaiho.project.domain.article.ArticleComment;
import me.parkdaiho.project.domain.GoodOrBad;
import me.parkdaiho.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoodOrBadRepository extends JpaRepository<GoodOrBad, Long> {

    Optional<GoodOrBad> findByArticleCommentAndUser(ArticleComment articleComment, User user);
}
