package me.parkdaiho.project.repository;

import me.parkdaiho.project.domain.Post;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitleContaining(String query, Pageable pageable);
    Page<Post> findByTextContaining(String query, Pageable pageable);
    Page<Post> findByWriter(User writer, Pageable pageable);

    Page<Post> findByArticle(Article article, Pageable pageable);
    Page<Post> findByArticleAndTitleContaining(Article article, String query, Pageable pageable);
    Page<Post> findByArticleAndTextContaining(Article article, String query, Pageable pageable);
    Page<Post> findByArticleAndWriter(Article article, User writer, Pageable pageable);
}
