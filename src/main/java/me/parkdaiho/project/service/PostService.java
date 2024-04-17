package me.parkdaiho.project.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.properties.MessageProperties;
import me.parkdaiho.project.config.properties.PaginationProperties;
import me.parkdaiho.project.domain.*;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.IndexViewResponse;
import me.parkdaiho.project.dto.board.*;
import me.parkdaiho.project.repository.PostRepository;
import me.parkdaiho.project.service.user.UserService;
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
    private final UserService userService;

    private final PaginationProperties paginationProperties;
    private final MessageProperties messageProperties;

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

        if(!CookieUtils.checkViewed(request, response, Domain.POST, id)) post.addViews();

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

    public Page<PostListViewResponse> getPostListViewResponse(SearchPostRequest request) {
        Order order = Order.valueOf(request.getSort().toUpperCase());
        Pageable pageable = getPageable(request.getPage(), paginationProperties.getPostsPerPage(), order);

        String query = request.getQuery();
        Sort sort = request.getSearchSort() == null ? null : Sort.valueOf(request.getSearchSort().toUpperCase());
        if(sort == null || query == null) {
            return postRepository.findAll(pageable)
                    .map(entity -> new PostListViewResponse(entity));
        }

        return getPostListViewResponseBySearch(sort, query, pageable);
    }

    private Page<PostListViewResponse> getPostListViewResponseBySearch(Sort sort, String query, Pageable pageable) {
        Page<Post> posts;
        String message = null;
        switch (sort) {
            case TITLE -> posts = postRepository.findByTitleContaining(query, pageable);
            case CONTENTS -> posts = postRepository.findByContentsContaining(query, pageable);
            case WRITER -> {
                try {
                    User writer = userService.findByNickname(query);
                    posts = postRepository.findByWriter(writer, pageable);
                } catch (Exception e) {
                    posts = postRepository.findAll(pageable);
                    message = messageProperties.getNotFoundNickname();
                }
            }

            default -> throw new IllegalArgumentException("Unexpected Sort: " + sort.getProperty());
        }

        if(message == null && !posts.hasContent()) message = messageProperties.getNotFoundPosts();

        final String finalMessage = message;
        return posts.map(entity -> new PostListViewResponse(entity, finalMessage));
    }

    private Pageable getPageable(int page, int size, Order order) {
        org.springframework.data.domain.Sort pageableSort = null;
        switch (order) {
            case LATEST, POPULARITY, VIEWS -> pageableSort = org.springframework.data.domain.Sort.by(
                    org.springframework.data.domain.Sort.Direction.DESC, order.getProperty());
            case EARLIEST -> pageableSort = org.springframework.data.domain.Sort.by(
                    org.springframework.data.domain.Sort.Direction.ASC, order.getProperty());

            default -> throw new IllegalArgumentException("Unexpected order:" + order.getValue());
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
        model.addAttribute("items", posts.getContent());
    }

    public List<IndexViewResponse> getPostsForIndex(Order order) {
        Pageable pageable = getPageable(1, paginationProperties.getIndexViews(), order);
        Page<Post> posts = postRepository.findAll(pageable);

        switch (order) {
            case POPULARITY -> {
                return posts.stream()
                        .map(entity -> IndexViewResponse.builder()
                                .title(entity.getTitle())
                                .link("/" + Domain.POST.getDomainPl() + "/" + entity.getId())
                                .figure(entity.getGood())
                                .build()).toList();
            }
            case VIEWS -> {
                return posts.stream()
                        .map(entity -> IndexViewResponse.builder()
                                .title(entity.getTitle())
                                .link("/" + Domain.POST.getDomainPl() + "/" + entity.getId())
                                .figure(entity.getViews())
                                .build()).toList();
            }
            case COMMENTS -> {
                return posts.stream()
                        .map(entity -> IndexViewResponse.builder()
                                .title(entity.getTitle())
                                .link("/" + Domain.POST.getDomainPl() + "/" + entity.getId())
                                .figure(entity.getCommentsSize())
                                .build()).toList();
            }

            default -> throw new IllegalArgumentException("Unexpected order: " + order.getValue());
        }
    }
}
