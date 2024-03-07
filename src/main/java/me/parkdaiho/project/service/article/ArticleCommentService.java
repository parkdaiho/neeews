package me.parkdaiho.project.service.article;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.article.ArticleComment;
import me.parkdaiho.project.dto.article.AddArticleCommentRequest;
import me.parkdaiho.project.dto.article.AddReplyRequest;
import me.parkdaiho.project.dto.article.ArticleCommentViewResponse;
import me.parkdaiho.project.dto.article.SearchNaverNewsRequest;
import me.parkdaiho.project.repository.article.ArticleCommentRepository;
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

    private final int COMMENTS_PER_PAGE = 5;

    public Page<ArticleCommentViewResponse> getDefaultArticleComments(Long id) {
        Pageable pageable = PageRequest.of(0, COMMENTS_PER_PAGE, Sort.Direction.DESC);

        Article article = articleService.findArticleById(id);

        return articleCommentRepository.findByArticleOrderByCreatedAt(pageable, article)
                .map(entity -> new ArticleCommentViewResponse(entity));
    }

    public Page<ArticleCommentViewResponse> getArticleCommentView(int page, String sort, Long id) {
        Pageable pageable = PageRequest.of(page - 1, COMMENTS_PER_PAGE, Sort.Direction.DESC);

        Article article = articleService.findArticleById(id);

        if(sort.equals("good")) {
            return articleCommentRepository.findByArticleOrderByGood(pageable, article)
                    .map(entity -> new ArticleCommentViewResponse(entity));
        }

        return articleCommentRepository.findByArticleOrderByCreatedAt(pageable, article)
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
