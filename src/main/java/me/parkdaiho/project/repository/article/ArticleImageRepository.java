package me.parkdaiho.project.repository.article;

import me.parkdaiho.project.domain.article.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
}
