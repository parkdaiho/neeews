package me.parkdaiho.project.service.user;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.oauth2.OAuth2AuthenticationCustomException;
import me.parkdaiho.project.config.oauth2.OAuth2UserInfo;
import me.parkdaiho.project.config.properties.PaginationProperties;
import me.parkdaiho.project.domain.Sort;
import me.parkdaiho.project.domain.user.RefreshToken;
import me.parkdaiho.project.domain.user.Role;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.user.SignUpRequest;
import me.parkdaiho.project.dto.user.SignUpResponse;
import me.parkdaiho.project.dto.user.UserInfoResponse;
import me.parkdaiho.project.repository.user.RefreshTokenRepository;
import me.parkdaiho.project.repository.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final PaginationProperties paginationProperties;

    public SignUpResponse signUp(SignUpRequest dto) {
        refreshTokenRepository.save(new RefreshToken(dto.toEntity()));

        return new SignUpResponse(dto.getUsername(), dto.getPassword());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user_id: " + id));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected username: " + username));
    }

    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected nickname: " + nickname));
    }

    public Boolean isUser(PrincipalDetails principal) {
        User user = principal.getUser();
        Role role = user.getRole();

        return role.getIsUser();
    }

    public void addAttributesForMyPage(PrincipalDetails principal, Model model) {
        User user = principal.getUser();

        model.addAttribute("username", user.getUsername());
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("email", user.getEmail());
    }

    public Page<UserInfoResponse> getUsers(int page, String sort, String searchSort, String query) {
        Pageable pageable = PageRequest.of(page - 1, paginationProperties.getUsersPerPage());

        Sort sortEnum = Sort.valueOf(sort.toUpperCase());
        switch (sortEnum) {
            case ALL -> {
                return getAllUsers(query, searchSort, pageable);
            }
            case ADMIN -> {
                return getUsersByRoleAndQuery(Role.ADMIN, query, searchSort, pageable);
            }
            case MANAGER -> {
                return getUsersByRoleAndQuery(Role.MANAGER, query, searchSort, pageable);
            }
            case User -> {
                return getUsersByRoleAndQuery(Role.USER, query, searchSort, pageable);
            }

            default -> throw new IllegalArgumentException("Unexected sort : " + sort);
        }
    }

    private Page<UserInfoResponse> getUsersByRoleAndQuery(Role role, String query, String searchSort, Pageable pageable) {
        Sort searchSortEnum = Sort.valueOf(searchSort.toUpperCase());
        Page<User> users;
        switch (searchSortEnum) {
            case USERNAME -> users = userRepository.findByRoleAndUsernameContaining(role, query, pageable);
            case NICKNAME -> users = userRepository.findByRoleAndNicknameContaining(role, query, pageable);

            default -> throw new IllegalArgumentException("Unexpected search-sort: " + searchSort);
        }

        return users.map(entity -> new UserInfoResponse(entity));
    }

    private Page<UserInfoResponse> getAllUsers(String query, String searchSort, Pageable pageable) {
        if(query == null) {
            return userRepository.findAll(pageable)
                    .map(entity -> new UserInfoResponse(entity));
        }

        Sort searchSortEnum = Sort.valueOf(searchSort.toUpperCase());
        Page<User> users;
        switch (searchSortEnum) {
            case USERNAME -> users = userRepository.findByUsernameContaining(query, pageable);
            case NICKNAME -> users = userRepository.findByNicknameContaining(query, pageable);

            default -> throw new IllegalArgumentException("Unexpected search-sort: " + searchSort);
        }

        return users.map(entity -> new UserInfoResponse(entity));
    }

    public void addAttributesForMembership(int page, String sort, String searchSort, String query,
                                           PrincipalDetails principal,
                                           Model model) {
        Page<UserInfoResponse> users = getUsers(page, sort, searchSort, query);

        int totalPages = users.getTotalPages();
        int pageBlockNum = page / paginationProperties.getUserPagesPerBlock();
        int firstNumOfPageBlock = pageBlockNum * paginationProperties.getUserPagesPerBlock() + 1;
        int lastNumOfPageBlock = firstNumOfPageBlock + paginationProperties.getUserPagesPerBlock() - 1;
        if(lastNumOfPageBlock > totalPages) lastNumOfPageBlock = totalPages;

        int nextPage = users.hasNext() ? page + 1 : page;
        int previousPage = users.hasPrevious() ? page - 1 : page;
        String path = "/membership?sort=" + sort + "&query=" + query + "&page=";

        model.addAttribute("page", page);
        model.addAttribute("sort", sort);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", users.getTotalElements());
        model.addAttribute("firstNumOfPageBlock", firstNumOfPageBlock);
        model.addAttribute("lastNumOfPageBlock", lastNumOfPageBlock);
        model.addAttribute("nextPage",  nextPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("path", path);
        model.addAttribute("users", users.getContent());

        User user = principal.getUser();
        model.addAttribute("isAdmin", user.getRole() == Role.ADMIN ? true : false);
    }

    public User getOAuth2UserByEmail(String email, OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new OAuth2AuthenticationCustomException("error", oAuth2UserInfo));
    }
}
