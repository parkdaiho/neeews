package me.parkdaiho.project.domain.board;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.BaseEntity;
import me.parkdaiho.project.domain.Comment;
import me.parkdaiho.project.domain.ImageFile;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.board.ModifyPostRequest;

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

    private Long views;

    @Builder
    public Post(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.writer = user;
    }

    @PrePersist
    public void prePersist() {
        views = 0L;
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
}
