package me.parkdaiho.project.domain;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "comments")
@Entity
public class Comment extends BaseEntity implements Polling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id", updatable = false)
    private User writer;

    private Long good;

    private Long bad;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Poll> pollList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> reply = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        pollList.add(Poll.builder()
                .user(writer)
                .comment(this)
                .build());

        this.good = 0L;
        this.bad = 0L;
    }

    @Builder
    public Comment(String contents, User writer) {
        this.contents = contents;
        this.writer = writer;
    }

    public void addReply(Comment reply) {
        reply.setParentComment(this);
        this.reply.add(reply);
    }

    public Long getGood() {
        return pollList.stream()
                .filter(e -> e.getFlag() != null && e.getFlag() == true)
                .count();
    }

    public Long getBad() {
        return pollList.stream()
                .filter(e -> e.getFlag() != null && e.getFlag() == false)
                .count();
    }
}
