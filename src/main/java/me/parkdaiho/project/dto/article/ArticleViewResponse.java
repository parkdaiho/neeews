package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.Article;
import me.parkdaiho.project.dto.comment.CommentViewResponse;

import java.util.List;

@Getter
@Setter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String text;
    private String imgLink;
    private String originalLink;
    private String pubDate;
    private Boolean isProvided;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.text = article.getContents();
        this.originalLink = article.getOriginalLink();
        this.pubDate = article.getPubDate();
        this.isProvided = article.getIsProvided();
    }
}
