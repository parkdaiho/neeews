package me.parkdaiho.project.dto.post;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.Post;
import me.parkdaiho.project.domain.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class NewPostRequest {

    private String title;
    private String text;
    private List<MultipartFile> files;

    public Post toEntity(User user) {
        return Post.builder()
                .title(title)
                .text(text)
                .user(user)
                .build();
    }
}
