package me.parkdaiho.project.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("message")
public class MessageProperties {

    // User
    private String notFoundNickname;

    // Post
    private String notFoundPosts;
}
