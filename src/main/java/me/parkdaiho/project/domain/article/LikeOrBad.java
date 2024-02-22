package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.parkdaiho.project.domain.BaseEntity;
import me.parkdaiho.project.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class LikeOrBad extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "comment_id", updatable = false)
    private ArticleComment articleComment;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    private Boolean likeOrBad;

    public void updateLike() {
        if(likeOrBad == null) {
            likeOrBad = true;

            return;
        }

        if(likeOrBad) {
            likeOrBad = null;
        }
    }

    public void updateBad() {
        if(likeOrBad == null) {
            likeOrBad = false;

            return;
        }

        if (!likeOrBad){
            likeOrBad = null;
        }
    }
}