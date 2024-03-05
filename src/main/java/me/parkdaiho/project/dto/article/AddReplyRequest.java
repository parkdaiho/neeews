package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddReplyRequest {

    private Long parentCommentId;
    private String contents;
}
