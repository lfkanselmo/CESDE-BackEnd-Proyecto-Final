package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {
    Owner save(Owner owner);

    void delete(Integer ownerId);

    Optional<Owner> getById(Integer id);

    Optional<List<Owner>> getByOwnerFirstName(String ownerFirstName);
}
