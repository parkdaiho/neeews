package me.parkdaiho.project.domain.user;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.BaseEntity;
import me.parkdaiho.project.domain.Comment;
import me.parkdaiho.project.domain.GoodOrBad;
import me.parkdaiho.project.domain.board.Post;

import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provider provider;

    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<GoodOrBad> goodOrBadListForComments;

    @OneToMany(mappedBy = "writer")
    private List<Post> posts;

    @PrePersist
    public void prePersist() {
        this.role = Role.USER;
        this.provider = this.provider == null ? Provider.SELF : this.provider;
        this.isEnabled = this.isEnabled == null || this.isEnabled;
    }

    @Builder
    public User(String username, String password, String nickname, String email, Provider provider) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.provider = provider;
    }
}
