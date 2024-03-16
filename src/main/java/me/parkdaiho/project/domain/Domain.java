package me.parkdaiho.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Domain {
    USER("users"), ARTICLE("articles"), POST("posts");

    public static Domain getDomainByDomainPl(String domainPl) {
        for(Domain domain : Domain.values()) {
            if(domainPl.equals(domain.getDomainPl())) {
                return domain;
            }
        }

        throw new IllegalArgumentException("Unexpected domainPl: " + domainPl);
    }

    private final String domainPl;
}
