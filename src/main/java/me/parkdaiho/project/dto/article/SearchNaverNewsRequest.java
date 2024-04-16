package me.parkdaiho.project.dto.article;

import lombok.*;

@Getter
@Setter
public class SearchNaverNewsRequest {

    private String query;
    private String sort;
    private int start;

    @Builder
    public SearchNaverNewsRequest(String query, String sort, int start) {
        this.query = query;
        this.sort = sort;
        this.start = start;
    }
}
