package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ArticleListResponse {

    private String title;
    private String description;
    private String url;
    private String createdAt;

    public ArticleListResponse(Article article) {
        this.title = article.getTitle();
        this.url = article.getArticleImages().get(0).getImgUrl();
        this.createdAt = article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
