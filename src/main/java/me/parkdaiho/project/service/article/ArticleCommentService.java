package me.parkdaiho.project.service.article;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleComment;
import me.parkdaiho.project.domain.GoodOrBad;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.article.AddArticleCommentRequest;
import me.parkdaiho.project.dto.article.AddReplyRequest;
import me.parkdaiho.project.dto.article.ArticleCommentViewResponse;
import me.parkdaiho.project.dto.article.SetGoodOrBadRequest;
import me.parkdaiho.project.repository.article.ArticleCommentRepository;
import me.parkdaiho.project.repository.article.GoodOrBadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleCommentService {

    private final ArticleService articleService;
    private final ArticleCommentRepository articleCommentRepository;
    private final GoodOrBadRepository goodOrBadRepository;

    private final int COMMENTS_PER_PAGE = 5;

    public Page<ArticleCommentViewResponse> getDefaultArticleComments(Long id) {
        Pageable pageable = PageRequest.of(0, COMMENTS_PER_PAGE, Sort.by(Sort.Direction.DESC, "createdAt"));

        Article article = articleService.findArticleById(id);

        return articleCommentRepository.findByArticle(pageable, article)
                .map(entity -> new ArticleCommentViewResponse(entity));
    }

    public Page<ArticleCommentViewResponse> getArticleCommentView(int page, String sort, Long id) {
        Pageable pageable = PageRequest.of(page - 1, COMMENTS_PER_PAGE, Sort.by(Sort.Direction.DESC, "createdAt"));

        Article article = articleService.findArticleById(id);

        return articleCommentRepository.findByArticle(pageable, article)
                .map(entity -> new ArticleCommentViewResponse(entity));
    }

    @Transactional
    public List<ArticleCommentViewResponse> addArticleComment(AddArticleCommentRequest dto, PrincipalDetails principal) {
        ArticleComment comment = articleCommentRepository.save(ArticleComment.builder()
                .contents(dto.getContents())
                .writer(principal.getUser())
                .build());

        Article article = articleService.findArticleById(dto.getArticleId());
        article.addArticleComment(comment);

        return getArticleComments(article);
    }

    @Transactional
    public List<ArticleCommentViewResponse> addReply(AddReplyRequest dto, PrincipalDetails principal) {
        ArticleComment reply = articleCommentRepository.save(ArticleComment.builder()
                .contents(dto.getContents())
                .writer(principal.getUser())
                .build());

        ArticleComment parentComment = findByParentCommentId(dto.getParentCommentId());
        parentComment.addReply(reply);

        Article article = parentComment.getArticle();

        return getArticleComments(article);
    }

    @Transactional
    public void setGoodOrBad(SetGoodOrBadRequest dto, PrincipalDetails principal) {
        ArticleComment comment = findById(dto.getCommentId());
        GoodOrBad goodOrBad = findGoodOrBadByCommentAndUser(comment, principal.getUser());

        Boolean flag = goodOrBad.getFlag();

        if(flag == null || flag != dto.getFlag()) {
            goodOrBad.setFlag(dto.getFlag());
        } else {
            goodOrBad.setFlag(null);
        }
    }

    private GoodOrBad findGoodOrBadByCommentAndUser(ArticleComment comment, User user) {
        return goodOrBadRepository.findByArticleCommentAndUser(comment, user)
                .orElseGet(
                        () -> goodOrBadRepository.save(
                                GoodOrBad.builder()
                                        .articleComment(comment)
                                        .user(user)
                                        .build()
                        )
                );
    }

    private ArticleComment findById(Long id) {
        return articleCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected comment: " + id));
    }

    private List<ArticleCommentViewResponse> getArticleComments(Article article) {
        return article.getComments().stream()
                .map(entity -> new ArticleCommentViewResponse(entity))
                .toList();
    }

    private ArticleComment findByParentCommentId(Long parentCommentId) {
        return articleCommentRepository.findById(parentCommentId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected ArticleComment"));
    }
}
