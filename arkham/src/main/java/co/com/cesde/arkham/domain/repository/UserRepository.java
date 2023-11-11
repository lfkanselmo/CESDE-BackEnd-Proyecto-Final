package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> save(User user);

    Optional<User> getByUserId(Integer id);

    void delete(Integer userId);

    Optional<User> getByUser(String user);
}
