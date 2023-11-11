package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Client;
import co.com.cesde.arkham.domain.repository.ClientRepository;
import co.com.cesde.arkham.persistence.crud.ClienteJpaRepository;
import co.com.cesde.arkham.persistence.entity.Cliente;
import co.com.cesde.arkham.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ClienteRepository implements ClientRepository {
    @Autowired
    private ClienteJpaRepository clienteJpaRepository;
    @Autowired
    private ClientMapper mapper;

    @Override
    public Optional<Client> save(Client client) {
        Cliente cliente = mapper.toCliente(client);
        return Optional.of(mapper.toclient(clienteJpaRepository.save(cliente)));
    }

    @Override
    public void delete(Integer clientId) {
        clienteJpaRepository.deleteById(clientId);
    }

    @Override
    public Optional<List<Client>> getByClientFirstName(String clientFirstName) {
        Optional<List<Cliente>> clientesOptional = clienteJpaRepository.findByNombreCliente(clientFirstName);
        return clientesOptional
                .map(clientes -> mapper.toclients(clientes));
    }

    @Override
    public Optional<Client> getByClientId(Integer id) {
        Optional<Cliente> clienteOptional = clienteJpaRepository.getByIdCliente(id);
        return clienteOptional.map(cliente -> mapper.toclient(cliente));
    }
}
