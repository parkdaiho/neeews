package me.parkdaiho.project.controller;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.ChangeRoleRequest;
import me.parkdaiho.project.dto.NicknameDupCheckRequest;
import me.parkdaiho.project.dto.NicknameDupCheckResponse;
import me.parkdaiho.project.dto.user.SignUpRequest;
import me.parkdaiho.project.dto.user.SignUpResponse;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest dto) {
        return ResponseEntity.ok(userService.signUp(dto));
    }

    @PostMapping("/api/membership")
    public ResponseEntity<Void> changeRole(@RequestBody ChangeRoleRequest request,
                                           @AuthenticationPrincipal PrincipalDetails principal) {
        userService.changeRole(request, principal);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/nickname")
    public ResponseEntity<NicknameDupCheckResponse> dupCheck(@RequestBody NicknameDupCheckRequest request,
                                                             @AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok(userService.dupCheck(request, principal));
    }
}
