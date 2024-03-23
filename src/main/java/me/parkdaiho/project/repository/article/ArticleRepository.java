package me.parkdaiho.project.repository.article;

import me.parkdaiho.project.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByLink(String link);
}
