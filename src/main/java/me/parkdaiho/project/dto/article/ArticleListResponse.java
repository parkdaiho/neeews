package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleListResponse {

    private String title;
    private String description;
    private String url;
    private String createdAt;
}
