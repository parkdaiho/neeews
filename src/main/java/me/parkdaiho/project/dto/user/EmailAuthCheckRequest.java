package me.parkdaiho.project.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAuthCheckRequest {

    private String email;
    private String code;
}
