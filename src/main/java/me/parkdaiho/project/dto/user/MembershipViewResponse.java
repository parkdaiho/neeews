package me.parkdaiho.project.dto.user;

import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.user.User;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class MembershipViewResponse {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String createdAt;
    private String modifiedAt;
    private String role;
    private Boolean isEnabled;

    public MembershipViewResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        this.modifiedAt= user.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        this.role = user.getRole().getAuthority();
        this.isEnabled = user.getIsEnabled();
    }
}
