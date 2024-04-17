package me.parkdaiho.project.domain;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.post.ModifyPostRequest;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id", nullable = false, updatable = false)
    private User writer;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<ImageFile> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private Long commentsSize;

    private Long views;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Poll> pollList = new ArrayList<>();

    private Long good;

    private Long bad;

    @Builder
    public Post(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.writer = user;
    }

    @PrePersist
    public void prePersist() {
        this.views = 0L;
        this.commentsSize = 0L;
        this.good = 0L;
        this.bad = 0L;
        this.isEnabled = true;
    }

    public void addImageFiles(List<ImageFile> files) {
        for(ImageFile file : files) {
            file.setPost(this);
            images.add(file);
        }
    }

    public void addComment(Comment comment) {
        comment.setPost(this);

        comments.add(comment);
        commentsSize++;
    }

    public Post modifyPost(ModifyPostRequest request) {
        this.title = request.getTitle();
        this.contents = request.getContents();

        return this;
    }

    public void initImages() {
        this.images = new ArrayList<>();
    }

    public void addViews() {
        this.views++;
    }

    public void syncWithPollList(Long good, Long bad) {
        this.good = good;
        this.bad = bad;
    }
}
