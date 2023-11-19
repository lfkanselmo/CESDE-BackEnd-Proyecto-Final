package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Client;
import co.com.cesde.arkham.domain.repository.ClientRepository;
import co.com.cesde.arkham.persistence.crud.ClienteJpaRepository;
import co.com.cesde.arkham.persistence.entity.Cliente;
import co.com.cesde.arkham.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Client save(Client client) {
        Cliente cliente = mapper.toCliente(client);
        return mapper.toclient(clienteJpaRepository.save(cliente));
    }

    @Override
    public void delete(Long clientId) {
        clienteJpaRepository.deleteById(clientId);
    }

    @Override
    public List<Client> getByClientFirstName(String clientFirstName) {
        List<Cliente> clientes = clienteJpaRepository.findByNombreClienteAndActivoTrue(clientFirstName);
        return mapper.toclients(clientes);
    }

    @Override
    public Optional<Client> getByClientId(Long id) {
        Optional<Cliente> clienteOptional = clienteJpaRepository.getByIdClienteAndActivoTrue(id);
        return clienteOptional
                .map(cliente -> mapper.toclient(cliente));
    }

    @Override
    public Page<Client> getAll(Pageable pagination) {
        Page<Cliente> clientes = clienteJpaRepository.findByActivoTrue(pagination);
        return clientes
                .map(cliente -> mapper.toclient(cliente));
    }

    @Override
    public Boolean existsById(Long clientId) {
        return clienteJpaRepository.existsById(clientId);
    }
}
