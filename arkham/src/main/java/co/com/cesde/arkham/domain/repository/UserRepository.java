package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    void delete(Long userId);

    Optional<User> getByUser(String user);
}
