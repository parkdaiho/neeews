package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleImage;

@Getter
@Setter
public class AddArticleResponse {

    private String title;
    private String contents;
    private String imgUrl;

    public AddArticleResponse(Article article, ArticleImage articleImage) {
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.imgUrl = articleImage.getImgUrl();
    }
}
