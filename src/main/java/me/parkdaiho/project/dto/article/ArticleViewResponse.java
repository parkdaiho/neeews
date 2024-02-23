package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ArticleViewResponse {

    private String title;
    private String contents;
    private String createdAt;

    public ArticleViewResponse(Article article) {
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.createdAt = article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
