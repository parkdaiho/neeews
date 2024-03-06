package me.parkdaiho.project.repository.article;

import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

    Page<ArticleComment> findByArticleOrderByCreatedAt(Pageable pageable, Article article);
    Page<ArticleComment> findByArticleOrderByGood(Pageable pageable, Article article);
}
