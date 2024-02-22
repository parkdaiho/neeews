package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "article_images")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Entity
public class ArticleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public ArticleImage(String imgUrl, Article article) {
        this.imgUrl = imgUrl;
        this.article = article;
    }
}
