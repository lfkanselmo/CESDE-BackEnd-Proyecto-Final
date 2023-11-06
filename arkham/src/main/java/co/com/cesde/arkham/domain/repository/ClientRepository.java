package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client create(Client client);

    Client update(Client client);

    void delete(Long clientId);

    Optional<List<Client>> getByClientFirstName(String clientFirstName);
}
