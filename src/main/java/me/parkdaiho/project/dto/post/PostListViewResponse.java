package me.parkdaiho.project.dto.post;

import lombok.Getter;
import me.parkdaiho.project.domain.Post;

import java.time.format.DateTimeFormatter;

@Getter
public class PostListViewResponse {

    private Long id;
    private String title;
    private String writer;
    private String createdAt;
    private String message;

    public PostListViewResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter().getNickname();
        this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-mm"));
    }

    public PostListViewResponse(Post post, String message) {
        this(post);
        this.message = message;
    }
}
