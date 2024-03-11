package me.parkdaiho.project.domain.board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.parkdaiho.project.domain.BaseEntity;
import me.parkdaiho.project.domain.GoodOrBad;
import me.parkdaiho.project.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "post_comments")
@Entity
public class PostComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id", nullable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "postComment", cascade = CascadeType.ALL)
    private List<GoodOrBad> goodOrBadList = new ArrayList<>();
}
