package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {
    Owner save(Owner owner);

    void delete(Long ownerId);

    Optional<Owner> getByOwnerId(Long ownerId);

    List<Owner> getByOwnerFirstName(String ownerFirstName);

    Page<Owner> getAll(Pageable pagination);

    Boolean existsById(Long ownerId);
}
