package me.parkdaiho.project.service.article;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleComment;
import me.parkdaiho.project.dto.article.AddArticleCommentRequest;
import me.parkdaiho.project.dto.article.AddReplyRequest;
import me.parkdaiho.project.dto.article.ArticleCommentViewResponse;
import me.parkdaiho.project.repository.article.ArticleCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleCommentService {

    private final ArticleService articleService;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional
    public List<ArticleCommentViewResponse> addArticleComment(AddArticleCommentRequest dto, PrincipalDetails principal) {
        ArticleComment comment = articleCommentRepository.save(ArticleComment.builder()
                .contents(dto.getContents())
                .writer(principal.getUser())
                .build());

        Article article = articleService.findArticleById(dto.getArticleId());
        article.addArticleComment(comment);

        System.out.println(comment.getArticle().getId());

        return getArticleComments(article);
    }

    @Transactional
    public List<ArticleCommentViewResponse> addReply(AddReplyRequest dto, PrincipalDetails principal) {
        ArticleComment reply = articleCommentRepository.save(ArticleComment.builder()
                .contents(dto.getContents())
                .writer(principal.getUser())
                .build());

        ArticleComment parentComment = findById(dto.getParentCommentId());
        parentComment.addReply(reply);

        Article article = parentComment.getArticle();

        return getArticleComments(article);
    }

    private List<ArticleCommentViewResponse> getArticleComments(Article article) {
        return article.getComments().stream()
                .map(entity -> new ArticleCommentViewResponse(entity))
                .toList();
    }

    private ArticleComment findById(Long parentCommentId) {
        return articleCommentRepository.findById(parentCommentId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected ArticleComment"));
    }
}
