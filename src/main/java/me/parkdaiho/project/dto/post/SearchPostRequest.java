package me.parkdaiho.project.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchPostRequest {

    private Integer page;
    private String order;
    private String query;
    private String searchSort;
}
