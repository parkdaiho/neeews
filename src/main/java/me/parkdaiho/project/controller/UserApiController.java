package me.parkdaiho.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.user.SendCodeForPasswordRequest;
import me.parkdaiho.project.dto.user.SendCodeForUsernameRequest;
import me.parkdaiho.project.dto.user.*;
import me.parkdaiho.project.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest dto) {
        return ResponseEntity.ok(userService.signUp(dto));
    }

    @PostMapping("/api/sign-up/username")
    public ResponseEntity<Boolean> checkUsername(@RequestBody UsernameDupCheckRequest request) {
        return ResponseEntity.ok(userService.usernameDupCheckInSignUp(request));
    }

    @PostMapping("/api/sign-up/nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestBody NicknameDupCheckRequest request) {
        return ResponseEntity.ok(userService.nicknameDupCheckInSignUp(request));
    }

    @PostMapping("/api/sign-up/email")
    public ResponseEntity<Boolean> checkEmail(@RequestBody EmailDupCheckRequest request) {
        return ResponseEntity.ok(userService.emailDupCheckInSignUp(request));
    }

    @PostMapping("/api/sign-up/email-auth")
    public ResponseEntity<Boolean> authEmail(@RequestBody EmailAuthCheckRequest request) {
        return ResponseEntity.ok(userService.emailAuthCheckInSignUp(request));
    }

    @PostMapping("/api/membership")
    public ResponseEntity<Void> changeRole(@RequestBody ChangeRoleRequest request,
                                           @AuthenticationPrincipal PrincipalDetails principal) {
        userService.changeRole(request, principal);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/membership")
    public ResponseEntity<Void> withdrawUser(@RequestBody WithdrawalMemberRequest request,
                                             @AuthenticationPrincipal PrincipalDetails principal) {
        userService.deleteUser(request, principal);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/information/nickname")
    public ResponseEntity<Boolean> nicknameDupCheck(@RequestBody NicknameDupCheckRequest request,
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
                                           @RequestBody WithdrawalMemberRequest dto,
                                           @AuthenticationPrincipal PrincipalDetails principal) {
        userService.deleteUser(request, response, dto, principal);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/username")
    public ResponseEntity<Void> sendCodeForUsername(@RequestBody SendCodeForUsernameRequest request) {
        userService.sendCode(request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/username/authentication")
    public ResponseEntity<Void> authenticateCodeForUsername(@RequestBody EmailAuthCheckRequest request,
                                                            HttpServletResponse response) {
        userService.emailAuthCheckInFindUsername(request, response);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/password")
    public ResponseEntity<Void> sendCodeForPassword(@RequestBody SendCodeForPasswordRequest request) {
        userService.sendCode(request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/password/authentication")
    public ResponseEntity<Void> authenticationForPassword(@RequestBody EmailAuthCheckRequest request,
                                                          HttpServletResponse response) {
        userService.emailAuthCheckInFindPassword(request, response);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest dto,
                                               HttpServletRequest request, HttpServletResponse response) {
        userService.changePassword(dto, request, response);

        return ResponseEntity.ok().build();
    }
}
