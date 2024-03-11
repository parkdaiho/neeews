package me.parkdaiho.project.service.board;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.dto.board.AddPostRequest;
import me.parkdaiho.project.repository.board.PostRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

}
