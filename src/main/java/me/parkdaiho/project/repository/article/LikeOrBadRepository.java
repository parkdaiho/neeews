package me.parkdaiho.project.repository.article;

import me.parkdaiho.project.domain.article.ArticleComment;
import me.parkdaiho.project.domain.article.LikeOrBad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeOrBadRepository extends JpaRepository<LikeOrBad, Long> {

    Optional<LikeOrBad> findByComment(ArticleComment comment);
    List<LikeOrBad> findByCommentAndFlag(ArticleComment comment, Boolean flag);
}
