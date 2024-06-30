package me.parkdaiho.project.dto.comment;

import lombok.Getter;
import me.parkdaiho.project.domain.Comment;

import java.time.format.DateTimeFormatter;

@Getter
public class MyCommentResponse {

    private String contents;
    private String createdAt;
    private String domain;
    private Long parentId;
    private Long good;
    private Long bad;

    public MyCommentResponse(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt().format(formatter);
        this.domain = comment.getParentDomain().getDomainPl();
        this.parentId = comment.getParentId();
        this.good = comment.getGood();
        this.bad = comment.getBad();
    }
}
