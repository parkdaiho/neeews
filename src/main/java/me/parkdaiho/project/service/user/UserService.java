package me.parkdaiho.project.service.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.oauth2.OAuth2AuthenticationCustomException;
import me.parkdaiho.project.config.oauth2.OAuth2UserInfo;
import me.parkdaiho.project.config.properties.JwtProperties;
import me.parkdaiho.project.config.properties.PaginationProperties;
import me.parkdaiho.project.domain.Sort;
import me.parkdaiho.project.domain.user.Token;
import me.parkdaiho.project.domain.user.Role;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.dto.user.*;
import me.parkdaiho.project.repository.user.TokenRepository;
import me.parkdaiho.project.repository.user.UserRepository;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final PaginationProperties paginationProperties;
    private final JwtProperties jwtProperties;

    public SignUpResponse signUp(SignUpRequest dto) {
        tokenRepository.save(new Token(dto.toEntity()));

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

        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("email", user.getEmail());
    }

    public Page<UserInfoResponse> getUsers(int page, String sort, String searchSort, String query) {
        Pageable pageable = PageRequest.of(page - 1, paginationProperties.getUsersPerPage());
        Sort sortEnum = Sort.valueOf(sort.toUpperCase());
        if(query == null || query.isEmpty()) {
            return getUsersBySort(sortEnum, pageable);
        }

        switch (sortEnum) {
            case ALL -> {
                return getAllUsersByQuery(query, searchSort, pageable);
            }
            case ADMIN -> {
                return getUsersByRoleAndQuery(Role.ADMIN, query, searchSort, pageable);
            }
            case MANAGER -> {
                return getUsersByRoleAndQuery(Role.MANAGER, query, searchSort, pageable);
            }
            case USER -> {
                return getUsersByRoleAndQuery(Role.USER, query, searchSort, pageable);
            }
            case WITHDRAWN -> {
                return getUsersByRoleAndQuery(Role.WITHDRAWN, query, searchSort, pageable);
            }

            default -> throw new IllegalArgumentException("Unexpected sort : " + sort);
        }
    }

    private Page<UserInfoResponse> getUsersBySort(Sort sort, Pageable pageable) {
        Page<User> users;
        switch (sort) {
            case ALL -> users = userRepository.findAll(pageable);
            case ADMIN, MANAGER, USER, WITHDRAWN -> {
                Role role = Role.valueOf(sort.getProperty());
                users = userRepository.findByRole(role, pageable);
            }

            default -> throw new IllegalArgumentException("Unexpected sort: " + sort);
        }

        return users.map(entity -> new UserInfoResponse(entity));
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

    private Page<UserInfoResponse> getAllUsersByQuery(String query, String searchSort, Pageable pageable) {
        Sort searchSortEnum = Sort.valueOf(searchSort);
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
        if (principal.getRole().getIsUser()) throw new IllegalArgumentException("No Authority");

        Page<UserInfoResponse> users = getUsers(page, sort, searchSort, query);

        int totalPages = users.getTotalPages();
        int pageBlockNum = (page - 1) / paginationProperties.getUserPagesPerBlock();
        int startNumOfPageBlock = pageBlockNum * paginationProperties.getUserPagesPerBlock() + 1;
        int lastNumOfPageBlock = startNumOfPageBlock + paginationProperties.getUserPagesPerBlock() - 1;
        if (lastNumOfPageBlock > totalPages) lastNumOfPageBlock = totalPages;

        int nextPage = users.hasNext() ? page + 1 : page;
        int previousPage = users.hasPrevious() ? page - 1 : page;

        model.addAttribute(paginationProperties.getPageName(), page);
        model.addAttribute("order", sort);
        model.addAttribute("searchSort", searchSort);
        model.addAttribute("query", query);
        model.addAttribute(paginationProperties.getTotalPagesName(), totalPages);
        model.addAttribute(paginationProperties.getTotalElementsName(), users.getTotalElements());
        model.addAttribute(paginationProperties.getStartNumOfPageBlockName(), startNumOfPageBlock);
        model.addAttribute(paginationProperties.getLastNumOfPageBlockName(), lastNumOfPageBlock);
        model.addAttribute(paginationProperties.getNextPageName(), nextPage);
        model.addAttribute(paginationProperties.getPreviousPageName(), previousPage);
        model.addAttribute("users", users.getContent());
        model.addAttribute("isAdmin", principal.getRole() == Role.ADMIN);
    }

    public User getOAuth2UserByEmail(String email, OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new OAuth2AuthenticationCustomException("error", oAuth2UserInfo));
    }

    @Transactional
    public void changeRole(ChangeRoleRequest request, PrincipalDetails principal) {
        checkAdmin(principal);

        User user = findById(request.getId());
        user.changeRole(request.getNewRole());
    }

    private void checkAdmin(PrincipalDetails principal) {
        User user = principal.getUser();
        Role role = user.getRole();

        if (!role.equals(Role.ADMIN)) {
            throw new IllegalArgumentException("No Authority");
        }
    }

    public NicknameDupCheckResponse nicknameDupCheckInMyPage(NicknameDupCheckRequest request, PrincipalDetails principal) {
        try {
            User user = findByNickname(request.getNickname());
            if (user.getId().equals(principal.getUserId())) return new NicknameDupCheckResponse(false, true);
            return new NicknameDupCheckResponse(false, false);
        } catch (Exception e) {
            return new NicknameDupCheckResponse(true, false);
        }
    }

    @Transactional
    public void modifyUser(ModifyUserInfoRequest request, PrincipalDetails principal) {
        User user = findById(request.getUserId());
        checkAuthority(user, principal);

        user.update(request.getPassword(), request.getNickname());
    }

    @Transactional
    public void deleteUser(HttpServletRequest request, HttpServletResponse response,
                           WithdrawalMemberRequest dto, PrincipalDetails principal) {
        User user = findById(dto.getUserId());
        checkAuthority(user, principal);

        user.checkPassword(dto.getPassword())
                .makeWithdrawnMember();

        CookieUtils.deleteCookie(request, response, jwtProperties.getRefreshTokenCookieName());
    }

    private void checkAuthority(User user, PrincipalDetails principal) {
        if (principal.getUserId().equals(user.getId()) || principal.getRole() != Role.USER) return;

        throw new IllegalArgumentException("No Authority");
    }

    @Transactional
    public void deleteUser(WithdrawalMemberRequest request, PrincipalDetails principal) {
        User user = findById(request.getUserId());
        checkAuthority(user, principal);

        user.makeWithdrawnMember();
    }
}
