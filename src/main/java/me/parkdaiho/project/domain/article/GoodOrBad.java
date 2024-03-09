package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "like_or_bad")
@Entity
public class GoodOrBad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id", updatable = false)
    private ArticleComment comment;

    // good == true, bad == false
    private Boolean flag;

    @Builder
    public GoodOrBad(User user, ArticleComment comment, Boolean flag) {
        this.user = user;
        this.comment = comment;
        this.flag = flag;
    }

    public void setGood() {
        flag = flag ? null : true;
    }

    public void setBad() {
        flag = !flag ? null : false;
    }
}
