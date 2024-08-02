package me.parkdaiho.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendCodeForPasswordRequest {

    private String username;
    private String email;
}
