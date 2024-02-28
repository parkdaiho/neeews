package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;

@Getter
@Setter
public class ArticleViewResponse {

    private String title;
    private String texts;
    private String imgLink;
    private String originalLink;
    private String pubDate;
    private Boolean isProvided;

    public ArticleViewResponse(Article article) {
        this.title = article.getTitle();
        this.texts = article.getTexts();
        this.imgLink = article.getImgLink();
        this.originalLink = article.getOriginalLink();
        this.pubDate = article.getPubDate();
        this.isProvided = article.getIsProvided();
    }
}
