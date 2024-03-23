package me.parkdaiho.project.repository.board;

import me.parkdaiho.project.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
