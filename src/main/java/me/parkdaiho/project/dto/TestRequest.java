package me.parkdaiho.project.dto;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleImage;
import me.parkdaiho.project.domain.article.Provider;

@Getter
@Setter
public class TestRequest {

    private String title;
    private String contents;
    private String imgUrl;
    private String provider;
    private String publishDate;

    public Article toArticle() {
        return Article.builder()
                .title(title)
                .contents(contents)
                .provider(provider)
                .publishDate(publishDate)
                .build();
    }

    public ArticleImage toArticleImage() {
        return ArticleImage.builder()
                .imgUrl(imgUrl)
                .build();
    }
}
