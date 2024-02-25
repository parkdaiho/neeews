package me.parkdaiho.project.dto.article;

import lombok.*;

@Getter
@Setter
public class SearchArticlesRequest {

    private String searchParam;
    private String searchSort;
}
