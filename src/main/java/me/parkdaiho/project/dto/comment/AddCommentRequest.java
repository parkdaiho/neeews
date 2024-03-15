package me.parkdaiho.project.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCommentRequest {

    private Long id;
    private String contents;
}
