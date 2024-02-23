package me.parkdaiho.project.service.article;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleComment;
import me.parkdaiho.project.domain.article.ArticleImage;
import me.parkdaiho.project.domain.article.LikeOrBad;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.article.*;
import me.parkdaiho.project.repository.article.ArticleCommentRepository;
import me.parkdaiho.project.repository.article.ArticleImageRepository;
import me.parkdaiho.project.repository.article.ArticleRepository;
import me.parkdaiho.project.repository.article.LikeOrBadRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleImageRepository articleImageRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final LikeOrBadRepository likeOrBadRepository;

    public Map<String, Object> getArticleView(Long id) {
        Map<String, Object> articleView = new HashMap<>();

        Article article = findArticleById(id);
        ArticleViewResponse articleViewResponse = new ArticleViewResponse(article);

        List<CommentViewResponse> commentViews = article.getArticleComments().stream()
                .map(CommentViewResponse::new)
                .toList();

        articleView.put("article", articleViewResponse);
        articleView.put("comments", commentViews);

        return articleView;
    }

    public AddArticleResponse newArticle(AddArticleRequest dto) {
        Article article = dto.toArticle();
        ArticleImage articleImage = dto.toArticleImage();

        article.addImage(articleImage);

        articleRepository.save(article);
        articleImageRepository.save(articleImage);

        return new AddArticleResponse(article, articleImage);
    }

    public List<AddCommentResponse> newComment(AddCommentRequest dto, PrincipalDetails principal) {
        Article article = findArticleById(dto.getArticleId().longValue());
        ArticleComment comment = ArticleComment.builder()
                .contents(dto.getComments())
                .writer(principal.getUser())
                .build();

        article.addComment(comment);

        articleCommentRepository.save(comment);

        return article.getArticleComments().stream()
                .map(AddCommentResponse::new)
                .toList();
    }

    public Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected article: " + id));
    }

    public PressLikeOrBadResponse pressLikeOrBad(PressLikeOrBadRequest dto, PrincipalDetails principal) {
        ArticleComment comment = findCommentById(dto.getCommentId());
        User user = principal.getUser();

        LikeOrBad likeOrBad = likeOrBadRepository.findByComment(comment)
                .orElse(likeOrBadRepository.save(
                        new LikeOrBad(user)
                ));

        comment.pressLikeOrBad(likeOrBad);

        if(dto.getFlag()) {
            likeOrBad.updateLike();
        } else {
            likeOrBad.updateBad();
        }

        Long like = (long) likeOrBadRepository.findByCommentAndFlag(comment, true).size();
        Long bad = (long) likeOrBadRepository.findByCommentAndFlag(comment, false).size();

        return new PressLikeOrBadResponse(comment.getId(), like, bad);
    }

    private ArticleComment findCommentById(Long commentId) {
        return articleCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected ArticleComment: " + commentId));
    }
}
