package me.parkdaiho.project.repository;

import me.parkdaiho.project.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Page<Notice> findByTitleContainsAndIsFixed(String query, Boolean isFixed, Pageable pageable);
    Page<Notice> findByTextContainingAndIsFixed(String query, Boolean isFixed, Pageable pageable);
    Page<Notice> findByIsFixed(Boolean isFixed, Pageable pageable);
}
