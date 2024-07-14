package me.parkdaiho.project.domain;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.post.ModifyPostRequest;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "posts")
@Entity
public class Post extends BaseEntity implements IncludingImages, Pollable, IncludingComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", updatable = false)
    private Article article;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id", nullable = false, updatable = false)
    private User writer;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<ImageFile> imageFiles = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private Long commentsSize;

    private Long views;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Poll> pollList = new ArrayList<>();

    private Long good;

    private Long bad;

    @Builder
    public Post(Article article, String title, String text, User user) {
        this.article = article;
        this.title = title;
        this.text = text;
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

    @Override
    public void addImageFiles(List<ImageFile> files) {
        for(ImageFile file : files) {
            file.setPost(this);

            imageFiles.add(file);
        }
    }

    @Override
    public void initImages() {
        this.imageFiles = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comment.setPost(this);

        comments.add(comment);
        commentsSize++;
    }

    public Post modifyPost(ModifyPostRequest request) {
        this.title = request.getTitle();
        this.text = request.getText();

        return this;
    }

    public void addViews() {
        this.views++;
    }

    public void syncWithPollList(Long good, Long bad) {
        this.good = good;
        this.bad = bad;
    }
}
