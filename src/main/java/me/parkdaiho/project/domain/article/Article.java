package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.BaseEntity;

import java.util.List;

@Table(name = "articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String texts;

    @Column(nullable = false)
    private String description;

    private String imgLink;

    @Column(nullable = false, unique = true)
    private String link;

    @Column(nullable = false, unique = true)
    private String originalLink;

    @Column(nullable = false)
    private String pubDate;

    private Boolean isProvided;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArticleComment> comments;

    @PrePersist
    public void prePersit() {
        this.isProvided = !link.equals(originalLink);
    }

    @Builder
    public Article(String title, String texts, String description, String link, String originalLink, String pubDate) {
        this.title = title;
        this.texts = texts;
        this.description = description;
        this.link = link;
        this.originalLink = originalLink;
        this.pubDate = pubDate;
    }
}
