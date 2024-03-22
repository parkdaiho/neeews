package me.parkdaiho.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class IndexViewResponse {

    private String title;
    private Long figure;
}
