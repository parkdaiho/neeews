package me.parkdaiho.project.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddReplyRequest {

    private Long parentCommentId;
    private String contents;
}
