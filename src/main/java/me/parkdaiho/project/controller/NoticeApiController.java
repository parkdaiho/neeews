package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.notice.NewNoticeRequest;
import me.parkdaiho.project.service.NoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping("/api/notice")
    public ResponseEntity<Void> writeNotice(NewNoticeRequest request,
                                            @AuthenticationPrincipal PrincipalDetails principal) throws IOException {
        Long savedNoticeId = noticeService.getSavedNoticeId(request, principal);

        return ResponseEntity.created(URI.create("/notice/" + savedNoticeId)).build();
    }

}
