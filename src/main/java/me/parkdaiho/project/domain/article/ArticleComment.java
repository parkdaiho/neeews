package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.BaseEntity;
import me.parkdaiho.project.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "article_comments")
@Entity
public class ArticleComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", nullable = false, updatable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer", nullable = false, updatable = false)
    private User writer;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<LikeOrBad> likeOrBadList = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.isEnabled = true;
    }

    @Builder
    public ArticleComment(String contents, Article article, User writer) {
        this.contents = contents;
        this.article = article;
        this.writer = writer;
    }

    public ArticleComment pressLikeOrBad(LikeOrBad likeOrBad) {
        likeOrBad.setComment(this);
        likeOrBadList.add(likeOrBad);

        return this;
    }
}
