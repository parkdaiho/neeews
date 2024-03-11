package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.BaseEntity;
import me.parkdaiho.project.domain.GoodOrBad;
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
    @JoinColumn(name = "article_id", nullable = true)
    private Article article;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "write_id", updatable = false)
    private User writer;

    @OneToMany(mappedBy = "articleComment", cascade = CascadeType.ALL)
    private List<GoodOrBad> goodOrBadList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_comment_id", updatable = false, nullable = true)
    private ArticleComment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<ArticleComment> reply = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        goodOrBadList.add(GoodOrBad.builder()
                .user(writer)
                .articleComment(this)
                .build());
    }

    @Builder
    public ArticleComment(String contents, User writer) {
        this.contents = contents;
        this.writer = writer;
    }

    public ArticleComment addReply(ArticleComment reply) {
        reply.setParentComment(this);
        this.reply.add(reply);

        return this;
    }

    public Long getGood() {
        return goodOrBadList.stream()
                .filter(e -> e.getFlag() != null && e.getFlag() == true)
                .count();
    }

    public Long getBad() {
        return goodOrBadList.stream()
                .filter(e -> e.getFlag() != null && e.getFlag() == false)
                .count();
    }
}
