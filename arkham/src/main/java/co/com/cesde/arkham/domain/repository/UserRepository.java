package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.domain.User;

import java.util.Optional;

public interface UserRepository {
    User create(User user);

    User update(User user);

    void delete(Long userId);

    Optional<User> getByUser(String user);
}
