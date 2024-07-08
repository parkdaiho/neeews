package me.parkdaiho.project.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("cookie-name")
public class CookieNameProperties {

    private String savedUserName;

    private String articles;
    private String posts;
    private String notice;
}
