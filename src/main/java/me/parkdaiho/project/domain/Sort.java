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
    ALL("ALL", "createdAt"), ROLE("role", "role");

    private final String value;
    private final String property;
}
