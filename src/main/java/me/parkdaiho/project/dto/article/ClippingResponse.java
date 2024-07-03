package me.parkdaiho.project.dto.article;

import lombok.Getter;
import me.parkdaiho.project.domain.article.Article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ClippingResponse {

    private Long id;
    private String title;
    private String pubDate;

    public ClippingResponse(Article article) {
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

        this.id = article.getId();
        this.title = article.getTitle();

        LocalDateTime pubDate = LocalDateTime.parse(article.getPubDate().trim(), formatter);
        this.pubDate = pubDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
