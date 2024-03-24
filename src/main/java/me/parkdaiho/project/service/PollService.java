package me.parkdaiho.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.*;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.PollRequest;
import me.parkdaiho.project.repository.PollRepository;
import me.parkdaiho.project.service.article.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PollService {

    private final PollRepository pollRepository;
    private final ArticleService articleService;
    private final PostService postService;
    private final CommentService commentService;

    @Transactional
    public void poll(PollRequest request, PrincipalDetails principal) {
        Long id = request.getId();
        Boolean flag = request.getFlag();
        Domain domain = Domain.getDomainByDomainPl(request.getDomain());

        User user = principal.getUser();

        switch (domain) {
            case ARTICLE -> pollInArticle(id, flag, user);
            case POST -> pollInPost(id, flag, user);
            case COMMENT -> pollInComment(id, flag, user);

            default -> throw new IllegalArgumentException("Unsuppored domain: " + domain.name());
        }
    }

    private void setPoll(Poll poll, Boolean flag) {
        if(poll.getFlag() == null || poll.getFlag() != flag) {
            poll.setFlag(flag);
        } else {
            poll.setFlag(null);
        }
    }

    private void pollInArticle(Long id, Boolean flag, User user) {
        Article article = articleService.findArticleById(id);
        Poll poll = findByArticleAndUser(article, user);

        setPoll(poll, flag);

        Long good = getValue(article.getPollList(), true);
        Long bad = getValue(article.getPollList(), false);

        article.syncWithPollList(good, bad);
    }

    private void pollInPost(Long id, Boolean flag, User user) {
        Post post = postService.findPostById(id);
        Poll poll = findByPostAndUser(post, user);

        setPoll(poll, flag);

        Long good = getValue(post.getPollList(), true);
        Long bad = getValue(post.getPollList(), false);

        post.syncWithPollList(good, bad);
    }

    private void pollInComment(Long id, Boolean flag, User user) {
        Comment comment = commentService.findCommentById(id);
        Poll poll = findByCommentAndUser(comment, user);

        setPoll(poll, flag);

        Long good = getValue(comment.getPollList(), true);
        Long bad = getValue(comment.getPollList(), false);

        comment.syncWithPollList(good, bad);
    }

    private Long getValue(List<Poll> pollList, Boolean flag) {
        return pollList.stream()
                .filter(entity -> entity.getFlag() != null && entity.getFlag() == flag)
                .count();
    }

    private Poll findByCommentAndUser(Comment comment, User user) {
        return pollRepository.findByCommentAndUser(comment, user)
                .orElseGet(() -> Poll.builder()
                        .comment(comment)
                        .user(user).build());
    }

    private Poll findByPostAndUser(Post post, User user) {
        return pollRepository.findByPostAndUser(post, user)
                .orElseGet(() -> Poll.builder()
                        .post(post)
                        .user(user).build());
    }

    private Poll findByArticleAndUser(Article article, User user) {
        return pollRepository.findByArticleAndUser(article, user)
                .orElseGet(() -> Poll.builder()
                        .article(article)
                        .user(user).build());
    }
}
