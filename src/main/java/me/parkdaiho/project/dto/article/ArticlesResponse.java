package me.parkdaiho.project.dto.article;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.Article;

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
        this.id = article.getId();
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.pubDate = article.getPubDate();
        this.views = article.getViews();
        this.good = article.getGood();
        this.commentsSize = article.getCommentsSize();
    }
}
