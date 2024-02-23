package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCommentRequest {

    private Integer articleId;
    private String comments;
}
