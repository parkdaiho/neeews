package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleImage;

@Getter
@Setter
public class AddArticleRequest {

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
