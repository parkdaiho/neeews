package me.parkdaiho.project.dto.board;

import lombok.Getter;
import me.parkdaiho.project.domain.board.Post;

import java.time.format.DateTimeFormatter;

@Getter
public class PostListViewResponse {

    private Long seq;
    private String title;
    private String writer;
    private String createdAt;

    public PostListViewResponse(Post post) {
        this.seq = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter().getNickname();
        this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-mm"));
    }
}
