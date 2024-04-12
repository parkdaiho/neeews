package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.*;
import me.parkdaiho.project.dto.user.SignUpRequest;
import me.parkdaiho.project.dto.user.SignUpResponse;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(userService.nicknameDupCheckInMyPage(request, principal));
    }

    @PutMapping("/api/information")
    public ResponseEntity<Void> modifyUser(@RequestBody ModifyUserInfoRequest request,
                                           @AuthenticationPrincipal PrincipalDetails principal) {
        userService.modifyUser(request, principal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/withdrawal")
    public ResponseEntity<Void> deleteUser(HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody WithdrawalRequest dto,
                                           @AuthenticationPrincipal PrincipalDetails principal) {
        userService.deleteUser(request, response, dto, principal);

        return ResponseEntity.ok().build();
    }
}
