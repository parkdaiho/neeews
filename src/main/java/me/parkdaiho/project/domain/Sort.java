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
    ALL("all", "ALL"),
    ADMIN("admin", "ADMIN"), MANAGER("manager", "MANAGER"), USER("user", "USER"),
    USERNAME("username", "USERNAME"), NICKNAME("nickname", "NICKNAME");

    private final String value;
    private final String property;
}
