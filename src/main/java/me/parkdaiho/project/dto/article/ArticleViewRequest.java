package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;

@Getter
@Setter
public class ArticleViewRequest {

    private String title;
    private String description;
    private String originalLink;
    private String link;
    private String pubDate;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .description(description)
                .originalLink(originalLink)
                .link(link)
                .pubDate(pubDate)
                .build();
    }
}
