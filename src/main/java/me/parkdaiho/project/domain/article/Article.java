package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "articles")
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

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(nullable = false)
    private String publishDate;

    @Column(nullable = false)
    private Long views;

    @OneToMany(mappedBy = "article")
    private List<ArticleImage> articleImages = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<ArticleComment> articleComments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.views = 0L;
        this.isEnabled = true;
    }

    @Builder
    public Article(String title, String contents, String provider, String publishDate) {
        this.title = title;
        this.contents = contents;
        this.provider = Provider.valueOf(provider);
        this.publishDate = publishDate;
    }

    public Article addImage(ArticleImage image) {
        image.setArticle(this);
        this.articleImages.add(image);

        return this;
    }

    public Article addComment(ArticleComment comment) {
        comment.setArticle(this);
        this.articleComments.add(comment);

        return this;
    }
}
