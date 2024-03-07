package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetArticleCommentRequest {

    private Long articleId;
    private Integer page;
    private String sort;
}
