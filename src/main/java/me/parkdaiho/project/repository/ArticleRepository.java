package me.parkdaiho.project.repository;

import me.parkdaiho.project.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
