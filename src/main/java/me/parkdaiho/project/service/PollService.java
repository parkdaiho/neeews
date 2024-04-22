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
    public void setPoll(PollRequest request, PrincipalDetails principal) {
        Long id = request.getId();
        User user = principal.getUser();

        Pollable pollable;
        Poll poll;
        Domain domain = Domain.getDomainByDomainPl(request.getDomain());
        switch (domain) {
            case ARTICLE -> {
                pollable = articleService.findArticleById(id);
                poll = findByArticleAndUser((Article) pollable, user);
            }
            case POST -> {
                pollable = postService.findPostById(id);
                poll = findByPostAndUser((Post) pollable, user);
            }
            case COMMENT -> {
                pollable = commentService.findCommentById(id);
                poll = findByCommentAndUser((Comment) pollable, user);
            }

            default -> throw new IllegalArgumentException("Unexpected domain: " + request.getDomain());
        }

        poll.updatePoll(request.getFlag());

        Long good = getValue(pollable.getPollList(), true);
        Long bad = getValue(pollable.getPollList(), false);

        pollable.syncWithPollList(good, bad);
    }

    private Long getValue(List<Poll> pollList, Boolean flag) {
        return pollList.stream()
                .filter(entity -> entity.getFlag() != null && entity.getFlag() == flag)
                .count();
    }

    private Poll findByCommentAndUser(Comment comment, User user) {
        return pollRepository.findByCommentAndUser(comment, user)
                .orElseGet(() -> pollRepository.save(Poll.builder()
                        .comment(comment)
                        .user(user).build()));
    }

    private Poll findByPostAndUser(Post post, User user) {
        return pollRepository.findByPostAndUser(post, user)
                .orElseGet(() -> pollRepository.save(Poll.builder()
                        .post(post)
                        .user(user).build()));
    }

    private Poll findByArticleAndUser(Article article, User user) {
        return pollRepository.findByArticleAndUser(article, user)
                .orElseGet(() -> pollRepository.save(Poll.builder()
                        .article(article)
                        .user(user).build()));
    }
}
