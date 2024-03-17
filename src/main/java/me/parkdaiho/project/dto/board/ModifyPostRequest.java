package me.parkdaiho.project.dto.board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ModifyPostRequest {

    private Long id;
    private String title;
    private String contents;
    private List<MultipartFile> files;
}
