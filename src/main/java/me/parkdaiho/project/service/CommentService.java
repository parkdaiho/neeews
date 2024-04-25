package me.parkdaiho.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.properties.PaginationProperties;
import me.parkdaiho.project.domain.*;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.comment.AddCommentRequest;
import me.parkdaiho.project.dto.comment.AddReplyRequest;
import me.parkdaiho.project.dto.comment.CommentViewResponse;
import me.parkdaiho.project.repository.CommentRepository;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final ArticleService articleService;
    private final PostService postService;
    private final NoticeService noticeService;

    private final PaginationProperties paginationProperties;

    public Page<CommentViewResponse> getDefaultComments(Long id, Domain domain) {
        return getCommentView(1, Order.LATEST, id, domain);
    }

    public Page<CommentViewResponse> getCommentView(int page, Order order,
                                                    Long id, Domain domain) {
        Pageable pageable = getPageable(page, order);
        Page<Comment> comments = getCommentsByParentId(id, domain, pageable);

        return comments.map(comment -> new CommentViewResponse(comment));
    }

    private Page<Comment> getCommentsByParentId(Long id, Domain domain, Pageable pageable) {
        switch (domain) {
            case ARTICLE -> {
                Article article = articleService.findArticleById(id);
                return commentRepository.findByArticle(pageable, article);
            }
            case POST -> {
                Post post = postService.findPostById(id);
                return commentRepository.findByPost(pageable, post);
            }
            case NOTICE -> {
                Notice notice = noticeService.findNoticeById(id);
                return commentRepository.findByNotice(pageable, notice);
            }

            default -> throw new IllegalArgumentException("Unexpected domain: " + domain.name());
        }
    }

    public void addCommentsInfoToModel(Page<CommentViewResponse> comments, Model model) {
        int pagesPerBlock = paginationProperties.getCommentPagesPerBlock();
        paginationProperties.addPaginationAttributesToModel(comments, model, pagesPerBlock);

        model.addAttribute("comments", comments.getContent());
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
            case NOTICE -> {
                Notice notice = noticeService.findNoticeById(dto.getId());
                notice.addComment(comment);
            }

            default -> throw new IllegalArgumentException("Unexpected domain: " + domain.name());
        }
    }

    @Transactional
    public void addReply(AddReplyRequest dto, PrincipalDetails principal) {
        Comment reply = commentRepository.save(Comment.builder()
                .contents(dto.getContents())
                .writer(principal.getUser())
                .build());

        Comment parentComment = findCommentById(dto.getParentCommentId());
        parentComment.addComment(reply);
    }

    public Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected comment: " + id));
    }

    private Pageable getPageable(int page, Order order) {
        org.springframework.data.domain.Sort pageableSort = null;
        switch (order) {
            case LATEST, POPULARITY, COMMENTS -> pageableSort = org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, order.getProperty())
                    .and(Sort.by(Sort.Direction.DESC, Order.LATEST.getProperty()));
            case EARLIEST -> pageableSort = org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC, order.getProperty());

            default -> throw new IllegalArgumentException("Unexpected order:" + order.getValue());
        }

        return PageRequest.of(page - 1, paginationProperties.getCommentsPerPage(), pageableSort);
    }

    @Transactional
    public void deleteComment(Long id, PrincipalDetails principal) {
        Comment comment = findCommentById(id);
        if(!comment.isWriter(principal) && principal.getRole().getIsUser()) {
            throw new IllegalArgumentException("No-authority");
        }

        commentRepository.delete(comment);
    }
}
