package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Client;
import co.com.cesde.arkham.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Optional<Client> save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getById(Integer id) {
        return clientRepository.getByClientId(id);
    }

    public Boolean delete(Integer id) {
        return getById(id).map(client -> {
            clientRepository.delete(id);
            return true;
        }).orElse(false);
    }
}
