package me.parkdaiho.project.domain;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "good_or_bad")
@Entity
public class GoodOrBad extends BaseEntity {

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
    @JoinColumn(name = "post_comment_id", updatable = false)
    private PostComment postComment;

    // good == true, bad == false
    private Boolean flag;

    @Builder
    public GoodOrBad(User user, Comment comment, PostComment postComment, Boolean flag) {
        this.user = user;
        this.comment = comment;
        this.postComment = postComment;
        this.flag = flag;
    }
}
