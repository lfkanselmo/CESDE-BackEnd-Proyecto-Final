package co.com.cesde.arkham.repository;



import co.com.cesde.arkham.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    List<Owner> getByOwnerFirstName(String ownerName);

    @Query(
            """
            update Owner o set o.active = false
            where o.ownerId = :ownerId
            """
    )
    void deleteOwner(Long ownerId);
}
