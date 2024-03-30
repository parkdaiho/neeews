package me.parkdaiho.project.repository;

import me.parkdaiho.project.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
