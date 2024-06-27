package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Article extends BaseEntity implements Pollable, IncludingComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String text;

    private String imgSrc;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    private String link;

    @Column(nullable = false, unique = true)
    private String originalLink;

    @Column(nullable = false)
    private String pubDate;

    private Long views;

    private Boolean isProvided;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private Long commentsSize;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Poll> pollList = new ArrayList<>();

    private Long good;

    private Long bad;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Clipping> clippings;

    @PrePersist
    public void prePersist() {
        this.isProvided = !link.equals(originalLink);
        this.views = 0L;
        this.commentsSize = 0L;
        this.good = 0L;
        this.bad = 0L;
        this.isEnabled = true;
    }

    @Builder
    public Article(String title, String text, String description, String link, String originalLink, String pubDate) {
        this.title = title;
        this.text = text;
        this.description = description;
        this.link = link;
        this.originalLink = originalLink;
        this.pubDate = pubDate;
    }

    public void addComment(Comment comment) {
        comment.setArticle(this);

        comments.add(comment);
        commentsSize++;
    }

    public void addViews() {
        this.views++;
    }

    public void syncWithPollList(Long good, Long bad) {
        this.good = good;
        this.bad = bad;
    }
}
