package me.parkdaiho.project.service;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.repository.PollRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PollService {

    private final PollRepository pollRepository;
}
