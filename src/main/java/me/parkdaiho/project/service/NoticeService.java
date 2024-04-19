package me.parkdaiho.project.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.ImageFile;
import me.parkdaiho.project.domain.Notice;
import me.parkdaiho.project.dto.notice.NewNoticeRequest;
import me.parkdaiho.project.dto.notice.NoticeViewResponse;
import me.parkdaiho.project.repository.NoticeRepository;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final ImageFileService imageFileService;

    @Transactional
    public Long getSavedNoticeId(NewNoticeRequest request, PrincipalDetails principal) throws IOException {
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
            System.out.println("id:" + notice.getId());
            imageFileService.moveFileToEntityDirectory(Domain.NOTICE, noticeRepository.save(notice), images);
            System.out.println("moveNoticeDirectory");
        } catch (Exception e) {
            imageFileService.removeSourceFile(images);
        }
    }

    public NoticeViewResponse getNoticeViewResponse(Long id, HttpServletRequest request, HttpServletResponse response) {
        Notice notice = findNoticeById(id);

        if (!CookieUtils.checkViewed(request, response, Domain.NOTICE, id)) notice.addViews();
        
        return new NoticeViewResponse(notice);
    }

    private Notice findNoticeById(Long id) {
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
}
