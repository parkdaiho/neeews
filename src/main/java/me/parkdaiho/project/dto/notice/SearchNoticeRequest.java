package me.parkdaiho.project.dto.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchNoticeRequest {

    private Integer page;
    private String order;
    private String searchSort;
    private String query;
}
