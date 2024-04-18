package me.parkdaiho.project.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.properties.PaginationProperties;
import me.parkdaiho.project.domain.*;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.IndexViewResponse;
import me.parkdaiho.project.dto.post.*;
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

        if (!CookieUtils.checkViewed(request, response, Domain.POST, id)) post.addViews();

        return new PostViewResponse(post, imageFileService.getPostSavedFileName(post.getImages()));
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
        Post post = findPostById(id);

        checkAuthority(post, principal);

        postRepository.delete(post);

        List<ImageFile> images = post.getImages();
        imageFileService.removeSavedFile(post, images);
    }

    @Transactional
    public Long getModifiedPostId(Long id, ModifyPostRequest request, PrincipalDetails principal) throws IOException {
        Post post = findPostById(id);

        checkAuthority(post, principal);

        post.modifyPost(request);

        if (request.getFiles() == null) return post.getId();

        List<ImageFile> newImages = imageFileService.modifyImages(post, request.getFiles());

        addImagesToPost(post, newImages);

        return post.getId();
    }

    public void checkAuthority(Post post, PrincipalDetails principal) {
        if (post.getWriter().getId() == principal.getUserId() || !principal.getRole().getIsUser()) return;

        throw new IllegalArgumentException("No authority");
    }

    public Page<PostListViewResponse> getPostListViewResponse(SearchPostRequest request) {
        Order order = Order.valueOf(request.getOrder().toUpperCase());
        Pageable pageable = getPageable(request.getPage(), paginationProperties.getPostsPerPage(), order);

        String query = request.getQuery();
        Sort searchSort = request.getSearchSort() == null ? null : Sort.valueOf(request.getSearchSort().toUpperCase());
        if (searchSort == null || query == null) {
            return postRepository.findAll(pageable)
                    .map(entity -> new PostListViewResponse(entity));
        }

        return getPostListViewResponseBySearch(searchSort, query, pageable);
    }

    private Page<PostListViewResponse> getPostListViewResponseBySearch(Sort searchSort, String query, Pageable pageable) {
        Page<Post> posts;
        switch (searchSort) {
            case TITLE -> posts = postRepository.findByTitleContaining(query, pageable);
            case TEXT -> posts = postRepository.findByTextContaining(query, pageable);
            case WRITER -> {
                try {
                    User writer = userService.findByNickname(query);
                    posts = postRepository.findByWriter(writer, pageable);
                } catch (Exception e) {
                    return null;
                }
            }

            default -> throw new IllegalArgumentException("Unexpected Sort: " + searchSort.getProperty());
        }

        return posts.map(entity -> new PostListViewResponse(entity));
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
        if(posts == null) return;

        int page = posts.getNumber() + 1;
        int totalPages = posts.getTotalPages();
        int firstNumOfPageBlock = page / paginationProperties.getPostPagesPerBlock() + 1;
        int lastNumOfPageBlock = firstNumOfPageBlock + paginationProperties.getPostPagesPerBlock() - 1;
        if (totalPages < lastNumOfPageBlock) lastNumOfPageBlock = totalPages;

        int nextPage = posts.hasNext() ? page + 1 : totalPages;
        int previousPage = posts.hasPrevious() ? page - 1 : page;

        model.addAttribute(paginationProperties.getPageName(), page);
        model.addAttribute(paginationProperties.getTotalPagesName(), totalPages);
        model.addAttribute(paginationProperties.getTotalElementsName(), posts.getTotalElements());
        model.addAttribute(paginationProperties.getStartNumOfPageBlockName(), firstNumOfPageBlock);
        model.addAttribute(paginationProperties.getLastNumOfPageBlockName(), lastNumOfPageBlock);
        model.addAttribute(paginationProperties.getNextPageName(), nextPage);
        model.addAttribute(paginationProperties.getPreviousPageName(), previousPage);
        model.addAttribute("posts", posts.getContent());
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

    public void addPostViewToModel(PostViewResponse post, Model model) {
        model.addAttribute("id", post.getId());
        model.addAttribute("title", post.getTitle());
        model.addAttribute("writer", post.getWriter());
        model.addAttribute("createdAt", post.getCreatedAt());
        model.addAttribute("modifiedAt", post.getModifiedAt());
        model.addAttribute("views", post.getViews());
        model.addAttribute("text", post.getText());
        model.addAttribute("savedFileNames", post.getSavedFileNames());
        model.addAttribute("good", post.getGood());
        model.addAttribute("bad", post.getBad());
    }

    public void addModifyViewToModel(ModifyViewResponse post, Model model) {
        model.addAttribute("id", post.getId());
        model.addAttribute("title", post.getTitle());
        model.addAttribute("text", post.getText());
    }
}
