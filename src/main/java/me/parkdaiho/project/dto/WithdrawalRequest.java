package me.parkdaiho.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawalRequest {

    private Long userId;
    private String password;
}
