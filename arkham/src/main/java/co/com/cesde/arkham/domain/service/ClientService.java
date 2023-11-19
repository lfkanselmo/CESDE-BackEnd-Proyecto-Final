package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Client;
import co.com.cesde.arkham.domain.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getById(Long clientId) {
        return clientRepository.getByClientId(clientId);
    }

    @Transactional
    public void delete(Long clientId) {
        Optional<Client> clientOptional = clientRepository.getByClientId(clientId);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setActive(false);
            clientRepository.save(client);
        }
    }

    public Page<Client> getAll(Pageable pagination) {
        return clientRepository.getAll(pagination);
    }

    @Transactional
    public Client update(Client client) {
        return clientRepository.save(client);
    }

    public boolean existsById(Long clientId) {
        return clientRepository.existsById(clientId);
    }
}
