package me.parkdaiho.project.dto.notice;

import lombok.Getter;
import me.parkdaiho.project.domain.Notice;

@Getter
public class ModifyViewResponse {

    private Long id;
    private String title;
    private String text;

    public ModifyViewResponse(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.text = notice.getText();
    }
}
