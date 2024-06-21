package me.parkdaiho.project.dto.notice;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.Notice;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class NoticeViewResponse {

    private Long id;
    private String title;
    private String createdAt;
    private String modifiedAt;
    private Long views;
    private String text;
    private List<String> savedFileName;

    public NoticeViewResponse(Notice notice) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

        this.id = notice.getId();
        this.title = notice.getTitle();
        this.createdAt = notice.getCreatedAt().format(formatter);
        this.modifiedAt = notice.getModifiedAt().format(formatter);
        this.views = notice.getViews();
        this.text = notice.getText();
        this.savedFileName = notice.getImageFiles().stream()
                .map(imageFile -> imageFile.getSavedName())
                .toList();
    }
}
