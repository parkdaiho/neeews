package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.Order;
import me.parkdaiho.project.dto.notice.ModifyViewResponse;
import me.parkdaiho.project.dto.notice.NoticeListViewResponse;
import me.parkdaiho.project.dto.notice.NoticeViewResponse;
import me.parkdaiho.project.dto.notice.SearchNoticeRequest;
import me.parkdaiho.project.service.NoticeService;
import org.springframework.data.domain.Page;
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
        if(request.getPage() == null) request.setPage(1);
        if(request.getOrder() == null) request.setOrder(Order.LATEST.getValue());

        Page<NoticeListViewResponse> fixedNoticeList = noticeService.getFixedNoticeList();
        Page<NoticeListViewResponse> noticeList = noticeService.getNoticeList(request, fixedNoticeList.getTotalElements());

        model.addAttribute("fixedNoticeList", fixedNoticeList.toList());
        model.addAttribute("order", request.getOrder());
        model.addAttribute("searchSort", request.getSearchSort());
        model.addAttribute("query", request.getQuery());

        noticeService.addNoticeListToModel(noticeList, model);

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

    @GetMapping("/notice")
    public String modifyNotice(Long id, Model model) {
        ModifyViewResponse notice = noticeService.getModifyViewResponse(id);

        noticeService.addModifyViewToModel(notice, model);

        return "new-notice";
    }
}
