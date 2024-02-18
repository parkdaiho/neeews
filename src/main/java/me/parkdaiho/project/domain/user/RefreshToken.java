package me.parkdaiho.project.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(nullable = false)
    private String refreshToken;

    @Builder
    public RefreshToken(PrincipalDetails principal, String refreshToken) {
        this.user = principal.getUser();
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String refreshToken) {
        this.refreshToken = refreshToken;

        return this;
    }
}
