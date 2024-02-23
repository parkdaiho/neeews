package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PressLikeOrBadRequest {

    private Long commentId;
    private Boolean flag;
}
