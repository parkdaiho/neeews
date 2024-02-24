package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.BaseEntity;
import me.parkdaiho.project.domain.user.User;

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

    @Column(nullable = false)
    private String publishDate;

    @Column(nullable = false)
    private Long views;



    @OneToOne(fetch = FetchType.EAGER)
    private User writer;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArticleImage> articleImages = new ArrayList<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArticleComment> articleComments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.views = 0L;
        this.isEnabled = true;
    }

    @Builder
    public Article(String title, String contents, String publishDate) {
        this.title = title;
        this.contents = contents;
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
