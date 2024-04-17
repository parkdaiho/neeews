package me.parkdaiho.project.dto.post;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.Post;

@Getter
@Setter
public class ModifyViewResponse {

    private Long id;
    private String title;
    private String contents;

    public ModifyViewResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getText();
    }
}
