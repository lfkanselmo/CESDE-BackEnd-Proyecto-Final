package co.com.cesde.arkham.domain.repository;

import co.com.cesde.arkham.persistence.crud.ClienteJpaRepository;
import co.com.cesde.arkham.persistence.entity.Cliente;

import java.util.List;
import java.util.Optional;

public class ClienteRepository {
    private ClienteJpaRepository clienteJpaRepository;

    public void create(Cliente cliente){
        clienteJpaRepository.save(cliente);
    }

    public void update(Cliente cliente){
        clienteJpaRepository.save(cliente);
    }

    public void delete(Long id){
        clienteJpaRepository.deleteById(id);
    }

    public Optional<List<Cliente>> getByNombre(String nombre){
        return clienteJpaRepository.findByNombreCliente(nombre);
    }
}
