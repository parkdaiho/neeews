package me.parkdaiho.project.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Sort {
    LATEST("latest", "createdAt"), EARLIEST("earliest", "createdAt"),
    POPULARITY("popularity", "good");

    private final String value;
    private final String property;
}
