package me.parkdaiho.project.service.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.domain.ImageFile;
import me.parkdaiho.project.domain.board.Post;
import me.parkdaiho.project.dto.board.AddPostRequest;
import me.parkdaiho.project.dto.board.ModifyPostRequest;
import me.parkdaiho.project.dto.board.ModifyViewResponse;
import me.parkdaiho.project.dto.board.PostViewResponse;
import me.parkdaiho.project.repository.board.PostRepository;
import me.parkdaiho.project.service.ImageFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

        addImagesToPost(post, images);

        return post.getId();
    }

    public void addImagesToPost(Post post, List<ImageFile> images) throws IOException {
        try {
            post.addImageFiles(images);

            Long savedPostId = postRepository.save(post).getId();
            imageFileService.moveFileToPostDirectory(images, savedPostId);

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
                .savedFileNames(imageFileService.getPostSavedFileName(post.getImages()))
                .build();
    }

    public Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected Post: " + id));
    }

    public ModifyViewResponse getModifyViewResponse(Long id) {
        return new ModifyViewResponse(findPostById(id));
    }



    @Transactional
    public void deletePost(Long id, PrincipalDetails principal) {
        Post post = checkAuthority(id, principal);

        postRepository.delete(post);

        List<ImageFile> images = post.getImages();
        imageFileService.removeSavedFile(id, images);
    }

    @Transactional
    public Long getModifiedPostId(Long id, ModifyPostRequest request, PrincipalDetails principal) throws IOException {
        Post post = checkAuthority(id, principal);
        post.modifyPost(request);

        if(request.getFiles() == null) return post.getId();

        List<ImageFile> existingImages = post.getImages();
        List<ImageFile> newImages = imageFileService.modifyImages(id, existingImages, request.getFiles());

        addImagesToPost(post, newImages);

        return post.getId();
    }

    public Post checkAuthority(Long id, PrincipalDetails principal) {
        Post post = findPostById(id);

        if(post.getWriter().getId() != principal.getUserId()) throw new IllegalArgumentException("No authority");

        return post;
    }
}
