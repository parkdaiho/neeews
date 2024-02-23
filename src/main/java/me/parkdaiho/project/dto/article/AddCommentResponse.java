package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.ArticleComment;
import me.parkdaiho.project.domain.article.LikeOrBad;

@Getter
@Setter
public class AddCommentResponse {

    private String writer;
    private String contents;
    private Long good;
    private Long bad;

    public AddCommentResponse(ArticleComment comment) {
        this.writer = comment.getWriter().getNickname();
        this.contents = comment.getContents();
        this.good = 0L;
        this.bad = 0L;
    }
}
