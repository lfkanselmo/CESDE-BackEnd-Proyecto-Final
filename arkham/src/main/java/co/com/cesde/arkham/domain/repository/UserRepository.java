package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.User;

import java.util.Optional;

public interface UserRepository {
    void create(User user);

    void update(User user);

    void delete(Long userId);

    Optional<User> getByUser(String user);
}
