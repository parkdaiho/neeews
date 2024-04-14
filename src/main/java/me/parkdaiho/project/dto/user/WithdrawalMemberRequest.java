package me.parkdaiho.project.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawalMemberRequest {

    private Long userId;
    private String password;
}
