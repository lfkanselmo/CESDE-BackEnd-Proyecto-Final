package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {
    Optional<Owner> save(Owner owner);

    void delete(Integer ownerId);

    Optional<Owner> getByOwnerId(Integer id);

    Optional<List<Owner>> getByOwnerFirstName(String ownerFirstName);

    Optional<Page<Owner>> getAll(Pageable pagination);
}
