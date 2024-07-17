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

//    public String getPubDate() {
//        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
//        LocalDateTime pubDate = LocalDateTime.parse(this.pubDate.trim(), formatter);
//
//        return pubDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }
//    error: Text '2024-07-17 13:05:00' could not be parsed at index 4
}
