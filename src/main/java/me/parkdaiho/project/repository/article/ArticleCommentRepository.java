package me.parkdaiho.project.repository.article;

import me.parkdaiho.project.domain.article.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
