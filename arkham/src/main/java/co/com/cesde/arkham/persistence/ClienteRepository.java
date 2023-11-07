package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Client;
import co.com.cesde.arkham.domain.repository.ClientRepository;
import co.com.cesde.arkham.persistence.crud.ClienteJpaRepository;
import co.com.cesde.arkham.persistence.entity.Cliente;
import co.com.cesde.arkham.persistence.mapper.ClientMapper;

import java.util.List;
import java.util.Optional;

public class ClienteRepository implements ClientRepository {
    private ClienteJpaRepository clienteJpaRepository;
    private ClientMapper mapper;

    @Override
    public Client create(Client client) {
        Cliente cliente = mapper.toCliente(client);
        clienteJpaRepository.save(cliente);
        return client;
    }

    @Override
    public Client update(Client client) {
        Cliente cliente = mapper.toCliente(client);
        clienteJpaRepository.save(cliente);
        return client;
    }

    @Override
    public void delete(Long clientId) {
        clienteJpaRepository.deleteById(clientId);
    }

    @Override
    public Optional<List<Client>> getByClientFirstName(String clientFirstName) {
        List<Cliente> clientes = clienteJpaRepository.findByNombreCliente(clientFirstName);
        return Optional.of(clientes
                .stream()
                .map(c -> mapper.toclient(c))
                .toList());
    }
}
