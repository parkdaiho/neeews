package me.parkdaiho.project.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Sort {

    // post, notice
    TITLE("title", "title"), TEXT("text", "text"),
    WRITER("writer", "writer"),
    // membership
    ALL("all", "ALL"), WITHDRAWN("withdrawn", "WITHDRAWN"),
    ADMIN("admin", "ADMIN"), MANAGER("manager", "MANAGER"), USER("user", "USER"),
    USERNAME("username", "USERNAME"), NICKNAME("nickname", "NICKNAME");

    private final String value;
    private final String property;
}
