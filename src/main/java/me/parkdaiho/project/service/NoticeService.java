package me.parkdaiho.project.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.properties.CookieNameProperties;
import me.parkdaiho.project.config.properties.PaginationProperties;
import me.parkdaiho.project.domain.*;
import me.parkdaiho.project.dto.notice.*;
import me.parkdaiho.project.repository.NoticeRepository;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final ImageFileService imageFileService;

    private final PaginationProperties paginationProperties;
    private final CookieNameProperties cookieNameProperties;

    @Transactional
    public Long getSavedNoticeId(NewNoticeRequest request, PrincipalDetails principal) throws IOException {
        checkAuthority(principal);

        Notice notice = request.toEntity(principal.getUser());
        List<ImageFile> files = imageFileService.uploadImageFiles(request.getFiles());

        if (files == null) return noticeRepository.save(notice).getId();

        addImagesToNotice(notice, files);

        return notice.getId();
    }

    private void addImagesToNotice(Notice notice, List<ImageFile> images) {
        try {
            notice.addImageFiles(images);

            noticeRepository.save(notice);
            imageFileService.moveFileToEntityDirectory(Domain.NOTICE, noticeRepository.save(notice), images);
        } catch (Exception e) {
            imageFileService.removeSourceFile(images);
        }
    }

    public NoticeViewResponse getNoticeViewResponse(Long id, HttpServletRequest request, HttpServletResponse response) {
        Notice notice = findNoticeById(id);

        if (!CookieUtils.checkViewed(request, response, cookieNameProperties.getViewedNotice(), id)) notice.addViews();

        return new NoticeViewResponse(notice);
    }

    public Notice findNoticeById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected Notice Id:" + id));
    }

    public void addNoticeViewToModel(NoticeViewResponse notice, Model model) {
        model.addAttribute("id", notice.getId());
        model.addAttribute("title", notice.getTitle());
        model.addAttribute("createdAt", notice.getCreatedAt());
        model.addAttribute("modifiedAt", notice.getModifiedAt());
        model.addAttribute("views", notice.getViews());
        model.addAttribute("text", notice.getText());
        model.addAttribute("savedFileNames", notice.getSavedFileName());
    }

    public ModifyViewResponse getModifyViewResponse(Long id) {
        return new ModifyViewResponse(findNoticeById(id));
    }

    public void addModifyViewToModel(ModifyViewResponse notice, Model model) {
        model.addAttribute("id", notice.getId());
        model.addAttribute("title", notice.getTitle());
        model.addAttribute("text", notice.getText());
        model.addAttribute("isFixed", String.valueOf(notice.getIsFixed()));
    }

    @Transactional
    public void deleteNotice(Long id, PrincipalDetails principal) {
        checkAuthority(principal);

        Notice notice = findNoticeById(id);

        noticeRepository.delete(notice);

        List<ImageFile> images = notice.getImageFiles();
        imageFileService.removeSavedFile(Domain.NOTICE, notice, images);
    }

    private void checkAuthority(PrincipalDetails principal) {
        if (!principal.getRole().getIsUser()) return;

        throw new IllegalArgumentException("No-Authority");
    }

    @Transactional
    public Long modifyNotice(ModifyNoticeRequest request, PrincipalDetails principal) throws IOException {
        checkAuthority(principal);

        Notice notice = findNoticeById(request.getId());
        notice.modify(request);

        if (request.getFiles() == null) return notice.getId();

        List<ImageFile> newImageFiles = imageFileService.modifyImages(Domain.NOTICE, notice, request.getFiles());

        addImagesToNotice(notice, newImageFiles);

        return notice.getId();
    }

    public Page<NoticeListViewResponse> getNoticeList(SearchNoticeRequest request, long fixedListSize) {
        Order order = Order.valueOf(request.getOrder().toUpperCase());
        Pageable pageable = getPageable(request.getPage(),
                (int) (paginationProperties.getNoticePerPage() - fixedListSize), order);

        String query = request.getQuery();
        if (query == null || query.isBlank()) {
            return noticeRepository.findByIsFixed(false, pageable)
                    .map(entity -> new NoticeListViewResponse(entity));
        }

        me.parkdaiho.project.domain.Sort sort = me.parkdaiho.project.domain.Sort.valueOf(request.getSearchSort().toUpperCase());
        Page<Notice> notice;
        switch (sort) {
            case TITLE -> notice = noticeRepository.findByTitleContainsAndIsFixed(request.getQuery(), false, pageable);
            case TEXT -> notice = noticeRepository.findByTextContainingAndIsFixed(request.getQuery(), false, pageable);

            default -> throw new IllegalArgumentException("Unexpected searchSort: " + sort.getValue());
        }

        return notice.map(entity -> new NoticeListViewResponse(entity));
    }

    private Pageable getPageable(int page, int size, Order order) {
        switch (order) {
            case LATEST, VIEWS, COMMENTS -> {
                return PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, order.getProperty())
                                .and(Sort.by(Sort.Direction.DESC, Order.LATEST.getProperty())));
            }

            default -> throw new IllegalArgumentException("Unexpected order: " + order.getValue());
        }
    }

    public void addNoticeListToModel(Page<NoticeListViewResponse> noticeList, Model model) {
        paginationProperties.addPaginationAttributesToModel(noticeList, model, paginationProperties.getNoticePagesPerBlock());

        model.addAttribute("noticeList", noticeList.toList());
    }

    public Page<NoticeListViewResponse> getFixedNoticeList() {
        Pageable pageable = getPageable(1, paginationProperties.getFixedNoticePerPage(), Order.LATEST);

        return noticeRepository.findByIsFixed(true, pageable)
                .map(entity -> new NoticeListViewResponse(entity));
    }
}
