package me.parkdaiho.project.service.user;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.repository.UserRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
}
