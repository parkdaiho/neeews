package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;

@Getter
@Setter
public class ArticleViewResponse {

    private String title;
    private String contents;
    private String originalLink;
    private String pubDate;

    public ArticleViewResponse(Article article) {
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.originalLink = article.getOriginalLink();
        this.pubDate = article.getPubDate();
    }
}
