package me.parkdaiho.project.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Provider {

    GOOGLE("GOOGLE"), SELF("SELF"), WITHDRAWN("withdrawn");

    private final String provider;
}
