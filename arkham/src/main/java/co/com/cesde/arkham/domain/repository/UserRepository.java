package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> getByUserId(Long userId);

    void delete(Long userId);

    Optional<User> getByUser(String userEmail);

    Boolean existsById(Long userId);
    UserDetails findByUserEmail(String username);
}
