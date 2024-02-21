package me.parkdaiho.project.service.article;

import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.Provider;
import me.parkdaiho.project.dto.TestRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    public void newArticle() {
        // given
        String title = "title";
        String contents = "contents";
        String provider = "TEST";
        String publishDate = "publishDate";
        String imgUrl = "url";

        TestRequest dto = new TestRequest();
        dto.setTitle(title);
        dto.setContents(contents);
        dto.setProvider(provider);
        dto.setPublishDate(publishDate);
        dto.setImgUrl(imgUrl);

        // when
        Article article = articleService.newArticle(dto);

        // then
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getContents()).isEqualTo(contents);
        assertThat(article.getProvider()).isEqualTo(Provider.valueOf(provider));
        assertThat(article.getArticleImages().get(0).getImgUrl()).isEqualTo(imgUrl);
    }
}