package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.repository.NoticeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class NoticeViewController {

    private final NoticeRepository noticeRepository;
}
