package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {
    void create(Owner owner);

    void update(Owner owner);

    void delete(Long ownerId);

    Optional<Owner> getById(Long id);

    Optional<List<Owner>> getByOwnerFirstName(String OwnerFirstName);
}
