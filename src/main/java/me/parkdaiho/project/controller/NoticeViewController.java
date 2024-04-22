package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.Order;
import me.parkdaiho.project.dto.comment.CommentViewResponse;
import me.parkdaiho.project.dto.notice.ModifyViewResponse;
import me.parkdaiho.project.dto.notice.NoticeListViewResponse;
import me.parkdaiho.project.dto.notice.NoticeViewResponse;
import me.parkdaiho.project.dto.notice.SearchNoticeRequest;
import me.parkdaiho.project.service.CommentService;
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
    private final CommentService commentService;

    @GetMapping("/new-notice")
    public String newNotice() {
        return "new-notice";
    }

    @GetMapping("/notice-list")
    public String noticeList(SearchNoticeRequest request, Model model) {
        if(request.getPage() == null) request.setPage(1);
        if(request.getOrder() == null || request.getOrder().isBlank()) request.setOrder(Order.LATEST.getValue());

        Page<NoticeListViewResponse> fixedNoticeList = noticeService.getFixedNoticeList();
        Page<NoticeListViewResponse> noticeList = noticeService.getNoticeList(request, fixedNoticeList.getTotalElements());

        model.addAttribute("order", request.getOrder());
        model.addAttribute("searchSort", request.getSearchSort());
        model.addAttribute("query", request.getQuery());

        model.addAttribute("fixedNoticeList", fixedNoticeList.toList());

        noticeService.addNoticeListToModel(noticeList, model);

        return "notice-list";
    }

    @GetMapping("/notice/{id}")
    public String notice(@PathVariable Long id,
                         HttpServletRequest request, HttpServletResponse response,
                         Model model) {
        NoticeViewResponse notice = noticeService.getNoticeViewResponse(id, request, response);
        Page<CommentViewResponse> comments = commentService.getDefaultComments(id, Domain.NOTICE);

        noticeService.addNoticeViewToModel(notice, model);
        commentService.addCommentsInfoToModel(comments, model);

        model.addAttribute("order", Order.LATEST.getValue());
        model.addAttribute("domain", Domain.NOTICE.getDomainPl());

        return "notice";
    }

    @GetMapping("/notice/{id}/comments")
    public String noticeCommentView(@PathVariable Long id,
                                    int page, String order,
                                    Model model) {
        Page<CommentViewResponse> comments = commentService.getCommentView(page, Order.valueOf(order.toUpperCase()), id, Domain.NOTICE);

        commentService.addCommentsInfoToModel(comments, model);

        return "comments-area";
    }

    @GetMapping("/notice")
    public String modifyNotice(Long id, Model model) {
        ModifyViewResponse notice = noticeService.getModifyViewResponse(id);

        noticeService.addModifyViewToModel(notice, model);

        return "new-notice";
    }
}
