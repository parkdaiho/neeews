package me.parkdaiho.project.service.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.properties.PaginationProperties;
import me.parkdaiho.project.domain.Domain;
import me.parkdaiho.project.domain.ImageFile;
import me.parkdaiho.project.domain.Sort;
import me.parkdaiho.project.domain.Post;
import me.parkdaiho.project.dto.IndexViewResponse;
import me.parkdaiho.project.dto.board.*;
import me.parkdaiho.project.repository.board.PostRepository;
import me.parkdaiho.project.service.ImageFileService;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ImageFileService imageFileService;

    private final PaginationProperties paginationProperties;

    @Transactional
    public Long getSavedPostId(AddPostRequest dto, PrincipalDetails principal) throws IOException {
        Post post = dto.toEntity(principal.getUser());
        List<ImageFile> images = imageFileService.uploadImageFiles(dto.getFiles());

        if (images == null) return postRepository.save(post).getId();

        addImagesToPost(post, images);

        return post.getId();
    }

    public void addImagesToPost(Post post, List<ImageFile> images) {
        try {
            post.addImageFiles(images);

            Long savedPostId = postRepository.save(post).getId();
            imageFileService.moveFileToPostDirectory(images, savedPostId);
        } catch (Exception e) {
            imageFileService.removeSourceFile(images);
        }
    }

    @Transactional
    public PostViewResponse getPostViewResponse(Long id, HttpServletRequest request, HttpServletResponse response) {
        Post post = findPostById(id);

        if(!CookieUtils.checkView(request, response, Domain.POST, id)) post.addViews();

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
        imageFileService.removeSavedFile(post, images);
    }

    @Transactional
    public Long getModifiedPostId(Long id, ModifyPostRequest request, PrincipalDetails principal) throws IOException {
        Post post = checkAuthority(id, principal);
        post.modifyPost(request);

        if (request.getFiles() == null) return post.getId();

        List<ImageFile> newImages = imageFileService.modifyImages(post, request.getFiles());

        addImagesToPost(post, newImages);

        return post.getId();
    }

    public Post checkAuthority(Long id, PrincipalDetails principal) {
        Post post = findPostById(id);

        if (post.getWriter().getId() != principal.getUserId()) throw new IllegalArgumentException("No authority");

        return post;
    }

    public Page<PostListViewResponse> getPostListViewResponse(int page, Sort sort) {
        Pageable pageable = getPageable(page, paginationProperties.getPostsPerPage(), sort);

        Page<Post> posts = postRepository.findAll(pageable);

        return posts.map(entity -> new PostListViewResponse(entity));
    }

    private Pageable getPageable(int size, int page, Sort sort) {
        org.springframework.data.domain.Sort pageableSort = null;
        switch (sort) {
            case LATEST, POPULARITY, VIEWS -> pageableSort = org.springframework.data.domain.Sort.by(
                    org.springframework.data.domain.Sort.Direction.DESC, sort.getProperty());
            case EARLIEST -> pageableSort = org.springframework.data.domain.Sort.by(
                    org.springframework.data.domain.Sort.Direction.ASC, sort.getProperty());

            default -> throw new IllegalArgumentException("Unexpected sort:" + sort.getValue());
        }

        return PageRequest.of(page - 1, size, pageableSort);
    }

    public void addPostsInfoToModel(Page<PostListViewResponse> posts, Model model) {
        int page = posts.getNumber() + 1;
        int totalPages = posts.getTotalPages();
        int firstNumOfPageBlock = page / paginationProperties.getPostPagesPerBlock() + 1;
        int lastNumOfPageBlock = firstNumOfPageBlock + paginationProperties.getPostPagesPerBlock() - 1;
        if(totalPages < lastNumOfPageBlock) lastNumOfPageBlock = totalPages;

        int nextPage = posts.hasNext() ? page + 1 : totalPages;
        int previousPage = posts.hasPrevious() ? page - 1 : page;

        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstNumOfPageBlock", firstNumOfPageBlock);
        model.addAttribute("lastNumOfPageBlock", lastNumOfPageBlock);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("previousPage", previousPage);
    }

    public List<IndexViewResponse> getPostsForIndex(Sort sort) {
        Pageable pageable = getPageable(paginationProperties.getIndexViews(), 1, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        switch (sort) {
            case POPULARITY -> {
                return posts.stream()
                        .map(entity -> IndexViewResponse.builder()
                                .title(entity.getTitle())
                                .figure(entity.getGood())
                                .build()).toList();
            }
            case VIEWS -> {
                return posts.stream()
                        .map(entity -> IndexViewResponse.builder()
                                .title(entity.getTitle())
                                .figure(entity.getViews())
                                .build()).toList();
            }

            default -> throw new IllegalArgumentException("Unexpected sort: " + sort.getValue());
        }
    }
}
