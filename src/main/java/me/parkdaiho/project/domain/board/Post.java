package me.parkdaiho.project.domain.board;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.BaseEntity;
import me.parkdaiho.project.domain.ImageFile;
import me.parkdaiho.project.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "posts")
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String contents;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<ImageFile> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id", nullable = false, updatable = false)
    private User user;

    private Long views;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostComment> comments;

    @Builder
    public Post(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        views = 0L;
    }
}
