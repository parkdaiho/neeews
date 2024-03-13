package me.parkdaiho.project.service.board;

import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.ImageFile;
import me.parkdaiho.project.domain.board.Post;
import me.parkdaiho.project.dto.board.AddPostRequest;
import me.parkdaiho.project.repository.ImageFileRepository;
import me.parkdaiho.project.repository.board.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ImageFileRepository imageFileRepository;

    public Long getSavedPostId(AddPostRequest dto, PrincipalDetails principal) {
        Post post = dto.toEntity(principal.getUser());

        List<ImageFile> images = saveImageFiles(dto.getFiles());
        post.addImageFiles(images);

        return postRepository.save(post).getId();
    }

    private List<ImageFile> saveImageFiles(List<MultipartFile> files) {
        List<ImageFile> images = new ArrayList<>();

        return images;
    }

}
