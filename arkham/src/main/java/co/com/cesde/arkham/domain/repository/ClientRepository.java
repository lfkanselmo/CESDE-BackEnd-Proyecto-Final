package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Optional<Client> save(Client client);

    void delete(Integer clientId);

    Optional<List<Client>> getByClientFirstName(String clientFirstName);

    Optional<Client> getByClientId(Integer id);

    Optional<Page<Client>> getAll(Pageable pagination);
}
