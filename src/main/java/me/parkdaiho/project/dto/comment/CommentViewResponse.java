package me.parkdaiho.project.dto.comment;

import lombok.Getter;
import me.parkdaiho.project.domain.Comment;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class CommentViewResponse {

    private Long id;
    private String writer;
    private String contents;
    private String createdAt;
    private Long good;
    private Long bad;
    private List<CommentViewResponse> replies;

    public CommentViewResponse(Comment comment) {
        this.id = comment.getId();
        this.writer = comment.getWriter().getNickname();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.good = comment.getGood();
        this.bad = comment.getBad();
        this.replies = comment.getReplies().stream()
                .map(entity -> new CommentViewResponse(entity))
                .toList();
    }
}
