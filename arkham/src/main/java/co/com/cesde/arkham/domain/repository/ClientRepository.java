package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);

    void delete(Long clientId);

    List<Client> getByClientFirstName(String clientFirstName);

    Optional<Client> getByClientId(Long clientId);

    Page<Client> getAll(Pageable pagination);

    Boolean existsById(Long clientId);
}
