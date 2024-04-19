package me.parkdaiho.project.dto.notice;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.Notice;
import me.parkdaiho.project.domain.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class NewNoticeRequest {

    private String title;
    private Boolean isFixed;
    private String text;
    private List<MultipartFile> files;

    public Notice toEntity(User user) {
        return Notice.builder()
                .title(title)
                .text(text)
                .writer(user)
                .isFixed(isFixed)
                .build();
    }
}
