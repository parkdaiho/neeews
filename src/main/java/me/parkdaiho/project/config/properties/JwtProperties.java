package me.parkdaiho.project.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {

    private String issuer;
    private String secretKey;

    private String authorizationHeaderName;
    private String authorizationTokenPrefix;

    private String refreshTokenCookieName;
    private int refreshTokenDurationOfDays;
    private int accessTokenDurationOfHours;

    public Duration getRefreshTokenDuration() {
        return Duration.ofDays(this.refreshTokenDurationOfDays);
    }

    public Duration getAccessTokenDuration() {
        return Duration.ofHours(this.accessTokenDurationOfHours);
    }
}
