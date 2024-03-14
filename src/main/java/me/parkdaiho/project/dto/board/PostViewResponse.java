package me.parkdaiho.project.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PostViewResponse {

    private Long id;
    private String title;
    private String contents;
    private List<String> imageFilePaths;


}
