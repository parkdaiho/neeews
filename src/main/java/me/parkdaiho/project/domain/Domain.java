package me.parkdaiho.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Domain {
    USER("user", "users"), ARTICLE("article", "articles"),
    POST("post", "posts"), COMMENT("comment", "comments");

    public static Domain getDomainByDomainPl(String domainPl) {
        for(Domain domain : Domain.values()) {
            if(domainPl.equals(domain.getDomainPl())) {
                return domain;
            }
        }

        throw new IllegalArgumentException("Unexpected domainPl: " + domainPl);
    }

    private final String domain;
    private final String domainPl;
}
