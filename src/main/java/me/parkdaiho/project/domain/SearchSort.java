package me.parkdaiho.project.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SearchSort {

    TITLE("title", "title"), CONTENTS("contents", "contents"),
    WRITER("writer", "writer");

    private final String value;
    private final String property;
}
