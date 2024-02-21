package me.parkdaiho.project.service.article;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleImage;
import me.parkdaiho.project.dto.TestRequest;
import me.parkdaiho.project.repository.ArticleImageRepository;
import me.parkdaiho.project.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleImageRepository articleImageRepository;

    public Article newArticle(TestRequest dto) {
        Article article = articleRepository.save(dto.toArticle());

        ArticleImage articleImage = dto.toArticleImage();
        article.addImage(articleImage);

        articleImageRepository.save(articleImage);


        return article;
    }
}
