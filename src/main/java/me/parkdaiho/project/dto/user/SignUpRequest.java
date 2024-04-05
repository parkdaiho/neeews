package me.parkdaiho.project.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import me.parkdaiho.project.domain.user.Provider;
import me.parkdaiho.project.domain.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Setter
@Getter
public class SignUpRequest {

    private String username;
    private String password;
    private String nickname;
    private String email;
    private String provider;

    public User toEntity() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(provider == null) provider = Provider.SELF.getProvider();

        return User.builder()
                .username(this.username)
                .password(passwordEncoder.encode(this.password))
                .nickname(this.nickname)
                .email(this.email)
                .provider(Provider.valueOf(provider))
                .build();
    }
}
