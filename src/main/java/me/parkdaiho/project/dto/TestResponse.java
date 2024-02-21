package me.parkdaiho.project.dto;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleImage;

@Getter
@Setter
public class TestResponse {

    private String title;
    private String contents;
    private String provider;
    private String imgUrl;

    public TestResponse(Article article, ArticleImage articleImage) {
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.provider = article.getProvider().getProvider();
        this.imgUrl = articleImage.getImgUrl();
    }
}
