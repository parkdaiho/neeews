package me.parkdaiho.project.dto;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.ArticleComment;

@Getter
@Setter
public class CommentTestResponse {

    private String writer;
    private String contents;
    private Long good;
    private Long bad;
    public CommentTestResponse(ArticleComment comment) {
        this.writer = comment.getWriter().getNickname();
        this.contents = comment.getContents();
    }
}
