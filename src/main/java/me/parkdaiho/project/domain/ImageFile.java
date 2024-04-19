package me.parkdaiho.project.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "image_files")
@Entity
public class ImageFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String savedName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", updatable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notice_id", updatable = false)
    private Notice notice;

    @PrePersist
    public void prePersist() {
        this.isEnabled = true;
    }

    @Builder
    public ImageFile(String originalName, String savedName, Post post) {
        this.originalName = originalName;
        this.savedName = savedName;
        this.post = post;
    }
}
