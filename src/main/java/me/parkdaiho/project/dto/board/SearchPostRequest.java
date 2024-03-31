package me.parkdaiho.project.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchPostRequest {

    private String query;
    private Integer page;
    private String sort;
    private String searchSort;
}
