package me.parkdaiho.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PollRequest {

    private Long id;
    private String domain;
    private Boolean flag;
}
