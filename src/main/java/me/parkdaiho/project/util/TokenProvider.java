package me.parkdaiho.project.util;

import io.jsonwebtoken.*;
import me.parkdaiho.project.config.properties.JwtProperties;
import me.parkdaiho.project.domain.user.User;

import java.time.Duration;
import java.util.Date;

public class TokenProvider {

    public static String generateAccessToken(User user, JwtProperties jwtProperties) {
        return generateToken(user, jwtProperties.getAccessTokenDuration(), jwtProperties);
    }

    public static String generateRefreshToken(User user, JwtProperties jwtProperties) {
        return generateToken(user, jwtProperties.getRefreshTokenDuration(), jwtProperties);
    }

    private static String generateToken(User user, Duration duration,
                                       JwtProperties jwtProperties) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setSubject(user.getNickname())
                .setExpiration(new Date(now.getTime() + duration.toMillis()))
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }
}
