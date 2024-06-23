package me.parkdaiho.project.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

    ADMIN("ADMIN", false), MANAGER("MANAGER", false),
    USER("USER", true), WITHDRAWN("WITHDRAWN", false);

    private final String authority;
    private final Boolean isUser;
}
