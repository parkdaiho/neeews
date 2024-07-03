package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String text;
    private String imgSrc;
    private String originalLink;
    private String pubDate;
    private Boolean isProvided;
    private Long views;
    private Long good;
    private Long bad;



    public ArticleViewResponse(Article article) {
        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

        this.id = article.getId();
        this.title = article.getTitle();
        this.text = article.getText();
        this.originalLink = article.getOriginalLink();

        LocalDateTime pubDate = LocalDateTime.parse(article.getPubDate().trim(), formatter);
        this.pubDate = pubDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        this.views = article.getViews();
        this.isProvided = article.getIsProvided();
        this.imgSrc = article.getImgSrc();
        this.good = article.getGood();
        this.bad = article.getBad();
    }
}
