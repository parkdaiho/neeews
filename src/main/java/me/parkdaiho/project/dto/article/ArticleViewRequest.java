package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;

@Getter
@Setter
public class ArticleViewRequest {

    private String title;
    private String link;
    private String originalLink;
    private String pubDate;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .link(link)
                .originalLink(originalLink)
                .pubDate(pubDate)
                .build();
    }
}
