package me.parkdaiho.project.domain;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.article.Article;
import me.parkdaiho.project.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "poll")
@Entity
public class Poll extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id", updatable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", updatable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", updatable = false)
    private Post post;

    // like == true, dislike == false
    private Boolean flag;

    @PrePersist
    public void prePersist() {
        this.isEnabled = true;
    }

    @Builder
    public Poll(User user, Comment comment, Article article, Post post) {
        this.user = user;
        this.comment = comment;
        this.article = article;
        this.post = post;
    }

    public void updatePoll(Boolean flag) {
        if(this.flag == null || this.flag != flag) {
            this.flag = flag;
        } else {
            this.flag = null;
        }
    }
}
