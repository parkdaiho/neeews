package me.parkdaiho.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    @Transactional
    public String sendAuthenticationCodeForSignUp (String email){
        String code = createAuthenticationCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Sign up authentication code");

        String text = """
                Authentication-code is "%s".
                Please input this code and sign-up.
                """.formatted(code);
        message.setText(text);

        emailSender.send(message);

        return code;
    }

    @Transactional
    public String sendAuthenticationCodeForUsername(String email) {
        String code = createAuthenticationCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Find username authentication code");

        String text = """
                Authentication-code is "%s".
                Please input this code and find your id.
                """.formatted(code);
        message.setText(text);

        emailSender.send(message);

        return code;
    }

    @Transactional
    public String sendAuthenticationCodeForPassword(String email) {
        String code = createAuthenticationCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Find password authentication code");

        String text = """
                Authentication-code is "%s".
                Please input this code and find your password.
                """.formatted(code);
        message.setText(text);

        emailSender.send(message);

        return code;
    }

    private String createAuthenticationCode() {
        StringBuilder authenticationNumber = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            int number = (int) (Math.random() * 10);
            authenticationNumber.append(number);
        }

        return authenticationNumber.toString();
    }

}
