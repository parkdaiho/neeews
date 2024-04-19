package me.parkdaiho.project.domain;

import jakarta.persistence.*;
import lombok.*;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.notice.ModifyNoticeRequest;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "notice")
@Entity
public class Notice extends BaseEntity implements IncludingImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageFile> imageFiles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id", nullable = false, updatable = false)
    private User writer;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private Boolean isFixed;

    private Long views;

    @PrePersist
    public void prePersist() {
        this.isEnabled = true;
        this.views = 0L;
    }

    @Builder
    public Notice(String title, String text, User writer, Boolean isFixed) {
        this.title = title;
        this.text = text;
        this.writer = writer;
        this.isFixed = isFixed;
    }

    @Override
    public void addImageFiles(List<ImageFile> files) {
        for (ImageFile image : files) {
            image.setNotice(this);

            imageFiles.add(image);
        }
    }

    @Override
    public void initImages() {
        this.imageFiles = new ArrayList<>();
    }

    public void addViews() {
        views++;
    }

    public Notice modify(ModifyNoticeRequest request) {
        this.title = request.getTitle();
        this.text = request.getText();

        return this;
    }
}
