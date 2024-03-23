package me.parkdaiho.project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Article extends BaseEntity implements Polling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String contents;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    private String link;

    @Column(nullable = false, unique = true)
    private String originalLink;

    @Column(nullable = false)
    private String pubDate;

    private Long views;

    private Long like;

    private Long bad;

    private Boolean isProvided;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    public void prePersit() {
        this.isProvided = !link.equals(originalLink);
        this.views = 0L;
        this.isEnabled = true;
    }

    @Builder
    public Article(String title, String contents, String description, String link, String originalLink, String pubDate) {
        this.title = title;
        this.contents = contents;
        this.description = description;
        this.link = link;
        this.originalLink = originalLink;
        this.pubDate = pubDate;
    }

    public void addComment(Comment comment) {
        comment.setArticle(this);
        comments.add(comment);
    }

    public void addViews() {
        this.views++;
    }
}
