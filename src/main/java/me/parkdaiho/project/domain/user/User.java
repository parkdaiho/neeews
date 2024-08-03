package me.parkdaiho.project.domain.user;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.*;
import me.parkdaiho.project.domain.article.Clipping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.UUID;

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Token token;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Poll> pollList;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "writer")
    private List<Notice> noticeList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Clipping> clippings;

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

    public void changeRole(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }

    public void update(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.password = passwordEncoder.encode(password);
    }

    public void update(String password, String nickname) {
        update(password);

        this.nickname = nickname;
    }

    public User checkPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(this.password, passwordEncoder.encode(password))) {
            throw new IllegalArgumentException("Check password");
        }

        return this;
    }

    public void makeWithdrawnMember() {
        this.password = invalidationField();
        this.email = invalidationField();
        this.role = Role.WITHDRAWN;

        this.token.deleteToken();

        this.isEnabled = false;
    }

    private String invalidationField() {
        return "withdrawn-member";
    }
}
