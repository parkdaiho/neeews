package me.parkdaiho.project.service.article;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.dto.article.ArticleViewRequest;
import me.parkdaiho.project.dto.article.ArticleViewResponse;
import me.parkdaiho.project.repository.article.ArticleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

//    public List<ArticleItem> searchNews(SearchArticlesRequest dto) {
//        Mono<SE>
//        return news;
//    }

    public Long findArticle(ArticleViewRequest dto) {
        Article article = null;

        try {
            article = findByLink(dto.getLink());
        } catch (IllegalArgumentException e) {
            article = addArticle(dto);
        }

        return article.getId();
    }

    public ArticleViewResponse getArticleViewResponse(Long id) {
        Article article = findById(id);

        return new ArticleViewResponse(article);
    }

    private Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected article"));
    }

    private Article addArticle(ArticleViewRequest dto) {
        String contents = getContents(dto.getLink());

        Article newArticle = dto.toEntity();
        newArticle.setContents(contents);

        return articleRepository.save(newArticle);
    }

    private String getContents(String link) {
        return "";
    }

    private Article findByLink(String link) {
        return articleRepository.findByLink(link)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected article"));
    }
}
