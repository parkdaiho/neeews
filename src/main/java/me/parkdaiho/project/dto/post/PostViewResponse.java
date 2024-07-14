package me.parkdaiho.project.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.Post;
import me.parkdaiho.project.domain.article.Article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class PostViewResponse {

    private Long id;
    private String title;
    private String writer;
    private String createdAt;
    private String modifiedAt;
    private Long views;
    private String text;
    private List<String> savedFileNames;
    private Long good;
    private Long bad;
    private Article article;

    public PostViewResponse(Post post) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter().getNickname();
        this.createdAt = post.getCreatedAt().format(dateTimeFormatter);
        this.modifiedAt = post.getModifiedAt().format(dateTimeFormatter);
        this.views = post.getViews();
        this.text = post.getText();
        this.good = post.getGood();
        this.bad = post.getBad();
        this.savedFileNames = post.getImageFiles().stream()
                .map(imageFile -> imageFile.getSavedName())
                .toList();
    }
}
