package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.BaseEntity;
import me.parkdaiho.project.domain.ImageFile;

import java.util.ArrayList;
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
    private String contents;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    private String link;

    @Column(nullable = false, unique = true)
    private String originalLink;

    @Column(nullable = false)
    private String pubDate;

    private Boolean isProvided;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArticleComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ImageFile> images = new ArrayList<>();

    @PrePersist
    public void prePersit() {
        this.isProvided = !link.equals(originalLink);
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

    public void addArticleComment(ArticleComment comment) {
        comment.setArticle(this);
        comments.add(comment);
    }

    public void addImageFiles(List<ImageFile> images) {
        for(ImageFile image : images) {
            image.setArticle(this);
            this.images.add(image);
        }
    }
}
