package me.parkdaiho.project.dto.notice;

import lombok.Getter;
import me.parkdaiho.project.domain.Notice;

import java.time.format.DateTimeFormatter;

@Getter
public class NoticeListViewResponse {

    private Long id;
    private String title;
    private String createdAt;

    public NoticeListViewResponse(Notice notice) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.id = notice.getId();
        this.title = notice.getTitle();
        this.createdAt = notice.getCreatedAt().format(formatter);
    }
}
