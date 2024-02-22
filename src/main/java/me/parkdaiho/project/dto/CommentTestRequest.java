package me.parkdaiho.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentTestRequest {

    private Integer articleId;
    private String comments;
}
