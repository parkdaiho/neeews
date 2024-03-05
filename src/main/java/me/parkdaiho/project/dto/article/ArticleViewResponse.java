package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleComment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String texts;
    private String imgLink;
    private String originalLink;
    private String pubDate;
    private Boolean isProvided;
    private List<ArticleCommentViewResponse> comments = new ArrayList<>();

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.texts = article.getContents();
        this.originalLink = article.getOriginalLink();
        this.pubDate = article.getPubDate();
        this.isProvided = article.getIsProvided();
        this.comments = article.getComments().stream()
                .map(entity -> new ArticleCommentViewResponse(entity))
                .toList();
    }
}
