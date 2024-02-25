package me.parkdaiho.project.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SearchArticlesResponse {

    private String title;
    private String description;
    private String link;
    private String originalLink;
    private String pubDate;
}
