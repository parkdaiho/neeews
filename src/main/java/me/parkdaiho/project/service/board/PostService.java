package me.parkdaiho.project.service.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.ImageFile;
import me.parkdaiho.project.domain.board.Post;
import me.parkdaiho.project.dto.board.AddPostRequest;
import me.parkdaiho.project.dto.board.PostViewResponse;
import me.parkdaiho.project.repository.board.PostRepository;
import me.parkdaiho.project.service.ImageFileService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ImageFileService imageFileService;

    @Transactional
    public Long getSavedPostId(AddPostRequest dto, PrincipalDetails principal) throws IOException {
        Post post = dto.toEntity(principal.getUser());
        List<ImageFile> images = imageFileService.uploadImageFiles(dto.getFiles());

        if(images == null) return postRepository.save(post).getId();

        try {
            post.addImageFiles(images);

            Long savedPostId = postRepository.save(post).getId();
            imageFileService.moveFileToPostDirectory(images, savedPostId);

            return savedPostId;
        } catch (Exception e) {
            imageFileService.removeSourceFile(images);

            throw e;
        }
    }

    public PostViewResponse getPostViewResponse(Long id) {
        Post post = findPostById(id);

        return PostViewResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contents(post.getContents())
                .imageFilePaths(imageFileService.getPostImageFilePath(post.getImages()))
                .build();
    }

    private Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected Post: " + id));
    }
}
