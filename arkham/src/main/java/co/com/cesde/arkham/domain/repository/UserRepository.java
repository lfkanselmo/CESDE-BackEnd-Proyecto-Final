package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.User;

import java.util.Optional;
public interface UserRepository {
    User save(User user);

    Optional<User> getByUserId(Long userId);

    void delete(Long userId);

    Optional<User> getByUsername(String userEmail);

    Boolean existsById(Long userId);
}
