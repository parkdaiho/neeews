package me.parkdaiho.project.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Sort {

    // post, notice
    TITLE("title", "title"), CONTENTS("contents", "contents"),
    WRITER("writer", "writer"),
    // membership
    ALL("all", "id"),
    ADMIN("admin", "ADMIN"), MANAGER("manager", "MANAGER"), User("user", "USER"),
    USERNAME("username", "username"), NICKNAME("nickname", "nickname");

    private final String value;
    private final String property;
}
