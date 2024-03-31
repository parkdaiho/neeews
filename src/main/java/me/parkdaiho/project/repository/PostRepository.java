package me.parkdaiho.project.repository;

import me.parkdaiho.project.domain.Post;
import me.parkdaiho.project.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitleContaining(String query, Pageable pageable);
    Page<Post> findByContentsContaining(String query, Pageable pageable);
    Page<Post> findByWriter(User writer, Pageable pageable);
}
