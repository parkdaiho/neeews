package me.parkdaiho.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAccessTokenRequest {

    private String refreshToken;
}
