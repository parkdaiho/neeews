package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ArticlesResponse {

    private Long id;
    private String title;
    private String description;
    private String pubDate;
    private Long figure;
    private Long views;
    private Long good;
    private Long commentsSize;

    public ArticlesResponse(Article article) {
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

        this.id = article.getId();
        this.title = article.getTitle();
        this.description = article.getDescription();

        LocalDateTime pubDate = LocalDateTime.parse(article.getPubDate().trim(), formatter);
        this.pubDate = pubDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        this.views = article.getViews();
        this.good = article.getGood();
        this.commentsSize = article.getCommentsSize();
    }
}
