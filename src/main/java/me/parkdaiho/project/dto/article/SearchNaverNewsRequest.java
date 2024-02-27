package me.parkdaiho.project.dto.article;

import lombok.*;

@Getter
@Setter
public class SearchNaverNewsRequest {

    private String query;
    private String sort;
}
