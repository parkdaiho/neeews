package me.parkdaiho.project.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Provider {
    GOOGLE("google"), SELF("self");

    private final String provider;
}
