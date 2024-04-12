package me.parkdaiho.project.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.sql.ConnectionBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Token {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    private String refreshToken;

    private String accessToken;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Token(User user) {
        this.user = user;
    }

    @Builder
    public Token(User user, String refreshToken, String accessToken) {
        this.user = user;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public Token updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;

        return this;
    }

    public Token updateAccessToken(String accessToken) {
        this.accessToken = accessToken;

        return this;
    }

    public Token deleteToken() {
        this.accessToken = "";
        this.refreshToken = "";

        return this;
    }
}
