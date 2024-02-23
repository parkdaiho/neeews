package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.ArticleComment;
import me.parkdaiho.project.domain.article.LikeOrBad;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class CommentViewResponse {

    private String writer;
    private String contents;
    private String createdAt;
    private Long like;
    private Long bad;

    public CommentViewResponse(ArticleComment comment) {
        this.writer = comment.getWriter().getNickname();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        List<LikeOrBad> likeOrBads = comment.getLikeOrBads();

        this.like = likeOrBads.stream()
                .filter(entity -> entity.getFlag())
                .count();

        this.bad = likeOrBads.stream()
                .filter(entity -> !entity.getFlag())
                .count();

    }
}
