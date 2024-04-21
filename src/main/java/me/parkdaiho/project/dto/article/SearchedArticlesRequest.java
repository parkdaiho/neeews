package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchedArticlesRequest {

    private String query;
    private String searchSort;
    private Integer page;
}
