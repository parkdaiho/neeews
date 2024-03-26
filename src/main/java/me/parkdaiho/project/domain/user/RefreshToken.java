package me.parkdaiho.project.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    private String refreshToken;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public RefreshToken(User user) {
        this.user = user;
    }

    @Builder
    public RefreshToken(User user, String refreshToken) {
        this.user = user;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String refreshToken) {
        this.refreshToken = refreshToken;

        return this;
    }
}
