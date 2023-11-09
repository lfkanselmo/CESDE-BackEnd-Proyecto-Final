package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);

    void delete(Integer clientId);

    Optional<List<Client>> getByClientFirstName(String clientFirstName);

    Optional<Client> getById(Integer id);
}
