package me.parkdaiho.project.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsItem {

    private String title;
    private String description;
    private String originallink;
    private String link;
    private String pubDate;
}
