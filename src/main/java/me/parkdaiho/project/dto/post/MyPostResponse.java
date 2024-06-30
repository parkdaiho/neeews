package me.parkdaiho.project.dto.post;

import lombok.Getter;
import me.parkdaiho.project.domain.Post;

import java.time.format.DateTimeFormatter;

@Getter
public class MyPostResponse {

    private Long id;
    private String title;
    private String createdAt;
    private Long views;
    private Long commentsSize;
    private Long good;
    private Long bad;

    public MyPostResponse(Post post) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.id = post.getId();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt().format(formatter);
        this.views = post.getViews();
        this.commentsSize = post.getCommentsSize();
        this.good = post.getGood();
        this.bad = post.getBad();
    }
}
