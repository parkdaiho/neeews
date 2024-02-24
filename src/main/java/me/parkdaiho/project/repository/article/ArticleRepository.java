package me.parkdaiho.project.repository.article;

import me.parkdaiho.project.domain.article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAll(Pageable pageable);
}
