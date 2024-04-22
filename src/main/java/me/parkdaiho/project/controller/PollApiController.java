package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.PollRequest;
import me.parkdaiho.project.service.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PollApiController {

    private final PollService pollService;

    @PutMapping("/api/poll")
    public ResponseEntity<Void> poll(@RequestBody PollRequest request, @AuthenticationPrincipal PrincipalDetails principal) {
        pollService.setPoll(request, principal);

        return ResponseEntity.ok().build();
    }
}