package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddArticleCommentRequest {

    private Long articleId;
    private String contents;
}
