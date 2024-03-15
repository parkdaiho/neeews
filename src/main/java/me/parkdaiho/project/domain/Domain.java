package me.parkdaiho.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Domain {
    USER("user"), ARTICLE("article"), POST("post");

    private final String domain;
}
