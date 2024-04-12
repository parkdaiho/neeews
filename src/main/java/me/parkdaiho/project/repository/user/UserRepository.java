package me.parkdaiho.project.repository.user;

import me.parkdaiho.project.domain.user.Role;
import me.parkdaiho.project.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Page<User> findByRoleAndNicknameContaining(Role role, String query, Pageable pageable);
    Page<User> findByRoleAndUsernameContaining(Role role, String query, Pageable pageable);
    Page<User> findByNicknameContaining(String query, Pageable pageable);
    Page<User> findByUsernameContaining(String query, Pageable pageable);
    Page<User> findByRole(Role role, Pageable pageable);
}
