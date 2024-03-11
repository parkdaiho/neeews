package me.parkdaiho.project.dto.board;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.board.Post;
import me.parkdaiho.project.domain.user.User;

@Getter
@Setter
public class AddPostRequest {

    private String title;
    private String contents;

    public Post toEntity(User user) {
        return Post.builder()
                .title(title)
                .contents(contents)
                .user(user)
                .build();
    }
}
