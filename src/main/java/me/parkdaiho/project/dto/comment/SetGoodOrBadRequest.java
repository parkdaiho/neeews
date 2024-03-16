package me.parkdaiho.project.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SetGoodOrBadRequest {

    private Long commentId;
    private Boolean flag;
}
