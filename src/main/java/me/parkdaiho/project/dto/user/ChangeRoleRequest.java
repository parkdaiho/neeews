package me.parkdaiho.project.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeRoleRequest {

    private Long id;
    private String newRole;
}
