package co.com.cesde.arkham.repository;


import co.com.cesde.arkham.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(
            """
            update Client c set c.active = false
            where c.clientId = :clientId
            """
    )
    void deleteClient(Long clientId);
}
