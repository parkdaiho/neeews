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
import me.parkdaiho.project.repository.PollRepository;
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
    private final PollRepository pollRepository;

    private final PaginationProperties paginationProperties;

    public Page<CommentViewResponse> getDefaultComments(Long id, Domain domain) {
        return getCommentView(1, me.parkdaiho.project.domain.Sort.LATEST, id, domain);
    }

    public Page<CommentViewResponse> getCommentView(int page, me.parkdaiho.project.domain.Sort sort, Long id, Domain domain) {
        Pageable pageable = getPageable(page, sort);
        Page<Comment> comments = getCommentsById(id, domain, pageable);

        return comments.map(comment -> new CommentViewResponse(comment));
    }

    private Page<Comment> getCommentsById(Long id, Domain domain, Pageable pageable) {
        switch (domain) {
            case ARTICLE -> {
                Article article = articleService.findArticleById(id);
                return commentRepository.findByArticle(pageable, article);
            }
            case POST -> {
                Post post = postService.findPostById(id);
                return commentRepository.findByPost(pageable, post);
            }

            default -> throw new IllegalArgumentException("Unexpected domain: " + domain.name());
        }
    }

    public void addCommentInfoToModel(Page<CommentViewResponse> comments, Model model) {
        int page = comments.getNumber() + 1;
        int totalPages = comments.getTotalPages();
        int firstNumOfPageBlock = page / paginationProperties.getCommentPagesPerBlock() * paginationProperties.getCommentPagesPerBlock() + 1;
        int lastNumOfPageBlock = firstNumOfPageBlock + paginationProperties.getCommentPagesPerBlock() - 1;
        if (totalPages < lastNumOfPageBlock) {
            lastNumOfPageBlock = totalPages;
        }

        int nextPage = comments.hasNext() ? page + 1 : totalPages;
        int previousPage = comments.hasPrevious() ? page - 1 : page;

        model.addAttribute("totalElements", comments.getTotalElements());
        model.addAttribute("page", page);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstNumOfPageBlock", firstNumOfPageBlock);
        model.addAttribute("lastNumOfPageBlock", lastNumOfPageBlock);
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
        parentComment.addReply(reply);
    }

    public Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected comment: " + id));
    }

    private Pageable getPageable(int page, me.parkdaiho.project.domain.Sort sort) {
        Sort pageableSort = null;
        switch (sort) {
            case LATEST, POPULARITY -> pageableSort = Sort.by(Sort.Direction.DESC, sort.getProperty());
            case EARLIEST -> pageableSort = Sort.by(Sort.Direction.ASC, sort.getProperty());

            default -> throw new IllegalArgumentException("Unexpected sort:" + sort.getValue());
        }

        return PageRequest.of(page - 1, paginationProperties.getCommentsPerPage(), pageableSort);
    }
}
