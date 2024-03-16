package me.parkdaiho.project.dto.comment;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.Comment;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommentViewResponse {

    private Long id;
    private String writer;
    private String contents;
    private String createdDate;
    private Long good;
    private Long bad;
    private List<CommentViewResponse> reply;

    public CommentViewResponse(Comment comment) {
        this.id = comment.getId();
        this.writer = comment.getWriter().getNickname();
        this.contents = comment.getContents();
        this.createdDate = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.good = comment.getGood();
        this.bad = comment.getBad();
        this.reply = comment.getReply().stream()
                .map(entity -> new CommentViewResponse(entity))
                .toList();
    }
}
