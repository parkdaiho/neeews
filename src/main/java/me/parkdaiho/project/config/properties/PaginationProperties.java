package me.parkdaiho.project.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("pagination")
public class PaginationProperties {

    private Integer usersPerPage;
    private Integer userPagesPerBlock;
    private Integer postsPerPage;
    private Integer postPagesPerBlock;
    private Integer commentsPerPage;
    private Integer commentPagesPerBlock;
    private Integer indexViews;
}
