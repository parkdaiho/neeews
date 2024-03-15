package me.parkdaiho.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.Comment;
import me.parkdaiho.project.domain.GoodOrBad;
import me.parkdaiho.project.domain.board.Post;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.comment.AddCommentRequest;
import me.parkdaiho.project.dto.comment.AddReplyRequest;
import me.parkdaiho.project.dto.comment.CommentViewResponse;
import me.parkdaiho.project.dto.article.SetGoodOrBadRequest;
import me.parkdaiho.project.repository.CommentRepository;
import me.parkdaiho.project.repository.GoodOrBadRepository;
import me.parkdaiho.project.service.article.ArticleService;
import me.parkdaiho.project.service.board.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final ArticleService articleService;
    private final PostService postService;

    private final GoodOrBadRepository goodOrBadRepository;

    private final int COMMENTS_PER_PAGE = 5;

    public Page<CommentViewResponse> getDefaultComments(Long id, Domain domain) {
        return getCommentView(1, "createdAt", id, domain);
    }

    public Page<CommentViewResponse> getCommentView(int page, String sort, Long id, Domain domain) {
        Pageable pageable = PageRequest.of(page - 1, COMMENTS_PER_PAGE, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Comment> comments = null;
        switch (domain) {
            case ARTICLE -> {
                Article article = articleService.findArticleById(id);
                comments = commentRepository.findByArticle(pageable, article);
            }
            case POST -> {
                Post post = postService.findPostById(id);
                comments = commentRepository.findByPost(pageable, post);
            }
            default -> {
                throw new IllegalArgumentException("Unexpected domain: " + domain.getDomain());
            }
        }

        return comments.map(comment -> new CommentViewResponse(comment));
    }

    @Transactional
    public void addComment(AddCommentRequest dto, PrincipalDetails principal, Domain domain) {
        Comment comment = commentRepository.save(Comment.builder()
                .contents(dto.getContents())
                .writer(principal.getUser())
                .build());

        switch (domain) {
            case ARTICLE -> {
                Article article = articleService.findArticleById(dto.getId());
                article.addComment(comment);
            }
            case POST -> {
                Post post = postService.findPostById(dto.getId());
                post.addComment(comment);
            }
            default -> {
                throw new IllegalArgumentException("Unexpected domain: " + domain.getDomain());
            }
        }
    }

    @Transactional
    public List<CommentViewResponse> addReply(AddReplyRequest dto, PrincipalDetails principal) {
        Comment reply = commentRepository.save(Comment.builder()
                .contents(dto.getContents())
                .writer(principal.getUser())
                .build());

        Comment parentComment = findByParentCommentId(dto.getParentCommentId());
        parentComment.addReply(reply);

        Article article = parentComment.getArticle();

        return getArticleComments(article);
    }

    @Transactional
    public void setGoodOrBad(SetGoodOrBadRequest dto, PrincipalDetails principal) {
        Comment comment = findById(dto.getCommentId());
        GoodOrBad goodOrBad = findGoodOrBadByCommentAndUser(comment, principal.getUser());

        Boolean flag = goodOrBad.getFlag();

        if(flag == null || flag != dto.getFlag()) {
            goodOrBad.setFlag(dto.getFlag());
        } else {
            goodOrBad.setFlag(null);
        }
    }

    private GoodOrBad findGoodOrBadByCommentAndUser(Comment comment, User user) {
        return goodOrBadRepository.findByArticleCommentAndUser(comment, user)
                .orElseGet(
                        () -> goodOrBadRepository.save(
                                GoodOrBad.builder()
                                        .comment(comment)
                                        .user(user)
                                        .build()
                        )
                );
    }

    private Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected comment: " + id));
    }

    private List<CommentViewResponse> getArticleComments(Article article) {
        return article.getComments().stream()
                .map(entity -> new CommentViewResponse(entity))
                .toList();
    }

    private Comment findByParentCommentId(Long parentCommentId) {
        return commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected Comment"));
    }
}
