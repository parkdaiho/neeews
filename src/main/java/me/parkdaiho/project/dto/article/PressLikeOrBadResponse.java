package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PressLikeOrBadResponse {

    private Long commentId;
    private Long like;
    private Long bad;

    public PressLikeOrBadResponse(Long commentId, Long like, Long bad) {
        this.commentId = commentId;
    }
}
