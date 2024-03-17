package me.parkdaiho.project.dto.board;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.board.Post;

@Getter
@Setter
public class ModifyViewResponse {

    private String title;
    private String contents;

    public ModifyViewResponse(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
    }
}
