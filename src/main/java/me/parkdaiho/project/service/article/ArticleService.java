package me.parkdaiho.project.service.article;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleComment;
import me.parkdaiho.project.domain.article.ArticleImage;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.CommentTestRequest;
import me.parkdaiho.project.dto.CommentTestResponse;
import me.parkdaiho.project.dto.TestRequest;
import me.parkdaiho.project.dto.TestResponse;
import me.parkdaiho.project.repository.article.ArticleCommentRepository;
import me.parkdaiho.project.repository.article.ArticleImageRepository;
import me.parkdaiho.project.repository.article.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleImageRepository articleImageRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public TestResponse newArticle(TestRequest dto) {
        Article article = dto.toArticle();
        ArticleImage articleImage = dto.toArticleImage();

        article.addImage(articleImage);

        articleRepository.save(article);
        articleImageRepository.save(articleImage);

        return new TestResponse(article, articleImage);
    }

    public List<CommentTestResponse> newComment(CommentTestRequest dto, PrincipalDetails principal) {
        Article article = findById(dto.getArticleId().longValue());
        ArticleComment comment = ArticleComment.builder()
                .contents(dto.getComments())
                .writer(principal.getUser())
                .build();

        article.addComment(comment);

        articleCommentRepository.save(comment);

        return article.getArticleComments().stream()
                .map(CommentTestResponse::new)
                .toList();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected article: " + id));
    }
}
