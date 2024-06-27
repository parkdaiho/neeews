package me.parkdaiho.project.repository;

import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.Comment;
import me.parkdaiho.project.domain.Poll;
import me.parkdaiho.project.domain.Post;
import me.parkdaiho.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long> {

    Optional<Poll> findByCommentAndUser(Comment comment, User user);
    Optional<Poll> findByArticleAndUser(Article article, User user);
    Optional<Poll> findByPostAndUser(Post post, User user);
}
