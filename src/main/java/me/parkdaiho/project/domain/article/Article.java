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

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false, unique = true)
    private String link;

    private String originalLink;

    @Column(nullable = false)
    private String pubDate;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArticleComment> comments;

    @Builder
    public Article(String title, String contents, String link, String originalLink, String pubDate) {
        this.title = title;
        this.contents = contents;
        this.link = link;
        this.originalLink = originalLink;
        this.pubDate = pubDate;
    }
}
