package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.dto.notice.NoticeViewResponse;
import me.parkdaiho.project.dto.notice.SearchNoticeRequest;
import me.parkdaiho.project.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class NoticeViewController {

    private final NoticeService noticeService;

    @GetMapping("/new-notice")
    public String newNotice() {
        return "new-notice";
    }

    @GetMapping("/notice-list")
    public String noticeList(SearchNoticeRequest request, Model model) {
        return "notice-list";
    }

    @GetMapping("/notice/{id}")
    public String notice(@PathVariable Long id,
                         HttpServletRequest request, HttpServletResponse response,
                         Model model) {
        NoticeViewResponse notice = noticeService.getNoticeViewResponse(id, request, response);

        noticeService.addNoticeViewToModel(notice, model);

        return "notice";
    }
}
