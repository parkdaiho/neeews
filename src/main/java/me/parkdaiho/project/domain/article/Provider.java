package me.parkdaiho.project.domain.article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Provider {

    TEST("TEST");

    private final String provider;
}
