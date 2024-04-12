package me.parkdaiho.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyUserInfoRequest {

    private Long userId;
    private String password;
    private String nickname;
}
