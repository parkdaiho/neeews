package me.parkdaiho.project.domain.article;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.BaseEntity;
import me.parkdaiho.project.domain.user.User;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class LikeOrBad extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_id", updatable = false)
    private ArticleComment comment;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    private Boolean flag;

    public LikeOrBad(User user) {
        this.user = user;
    }

    public void updateLike() {
        if(flag == null) {
            flag = true;

            return;
        }

        if(flag) {
            flag = null;
        }
    }

    public void updateBad() {
        if(flag == null) {
            flag = false;

            return;
        }

        if (!flag){
            flag = null;
        }
    }
}