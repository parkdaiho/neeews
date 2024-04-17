package me.parkdaiho.project.dto.article;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticlesResponse {

    private Long id;
    private String title;
    private String description;
    private String pubDate;
    private Long figure;

    @Builder
    public ArticlesResponse(Long id, String title, String description, String pubDate, Long figure) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.figure = figure;
    }
}
