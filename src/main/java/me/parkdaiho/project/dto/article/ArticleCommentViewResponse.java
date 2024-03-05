package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.article.ArticleComment;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArticleCommentViewResponse {

    private Long id;
    private String writer;
    private String contents;
    private String createdDate;
    private Long good;
    private Long bad;
    private List<ArticleCommentViewResponse> reply = new ArrayList<>();

    public ArticleCommentViewResponse(ArticleComment articleComment) {
        this.id = articleComment.getId();
        this.writer = articleComment.getWriter().getNickname();
        this.contents = articleComment.getContents();
        this.createdDate = articleComment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.good = articleComment.getGood();
        this.bad = articleComment.getBad();
        this.reply = articleComment.getReply().stream()
                .map(entity -> new ArticleCommentViewResponse(entity))
                .toList();
    }
}
