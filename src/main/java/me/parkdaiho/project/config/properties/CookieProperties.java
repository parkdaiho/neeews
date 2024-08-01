package me.parkdaiho.project.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("cookie")
public class CookieProperties {

    private String savedUsernameName;

    private String viewedArticlesName;
    private String viewedPostsName;
    private String viewedNoticeName;

    private String usernameInFindUsernameName;
    private String emailInFindPasswordName;

    private int savedUsernameExpiry;
    private int viewedCheckedExpiry;
    private int findUserInfoExpiry;
}
