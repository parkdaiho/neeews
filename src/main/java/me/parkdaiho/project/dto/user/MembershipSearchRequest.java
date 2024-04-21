package me.parkdaiho.project.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipSearchRequest {

    private Integer page;
    private String sort;
    private String searchSort;
    private String query;
}
