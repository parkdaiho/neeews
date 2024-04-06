package me.parkdaiho.project.service.user;

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

    public Page<UserInfoResponse> getUsers(int page, String sort, String query) {
        Pageable pageable = getPageable(page, sort);

        if (query == null) {
            return userRepository.findAll(pageable)
                    .map(entity -> new UserInfoResponse(entity));
        }

        return userRepository.findByNicknameContaining(query, pageable)
                .map(entity -> new UserInfoResponse(entity));
    }

    private Pageable getPageable(int page, String sort) {
        Sort sortEnum = Sort.valueOf(sort.toUpperCase());

        return PageRequest.of(page, paginationProperties.getUsersPerPage(),
                org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, sortEnum.getProperty()));
    }

    public void addAttributesForMembership(Page<UserInfoResponse> users, Model model) {
        int page = users.getNumber() + 1;
        int totalPages = users.getTotalPages();
        int pageBlockNum = page / paginationProperties.getUserPagesPerBlock();
        int firstNumOfPageBlock = pageBlockNum * paginationProperties.getUserPagesPerBlock() + 1;
        int lastNumOfPageBlock = firstNumOfPageBlock + paginationProperties.getUserPagesPerBlock() - 1;
        if(lastNumOfPageBlock > totalPages) lastNumOfPageBlock = totalPages;
        int nextPage = users.hasNext() ? page + 1 : page;
        int previousPage = users.hasPrevious() ? page - 1 : page;

        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalElements", users.getTotalElements());
        model.addAttribute("firstNumOfPageBlock", firstNumOfPageBlock);
        model.addAttribute("lastNumOfPageBlock", lastNumOfPageBlock);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("users", users.getContent());
    }

    public User getOAuth2UserByEmail(String email, OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new OAuth2AuthenticationCustomException("error", oAuth2UserInfo));
    }
}
