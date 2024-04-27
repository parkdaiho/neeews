package me.parkdaiho.project.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NicknameDupCheckRequest {

    private String originalNickname;
    private String nickname;
}
