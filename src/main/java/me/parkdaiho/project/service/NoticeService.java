package me.parkdaiho.project.service;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;


}
