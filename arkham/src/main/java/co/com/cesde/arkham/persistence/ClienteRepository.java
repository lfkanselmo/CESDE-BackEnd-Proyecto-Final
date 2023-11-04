package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.persistence.crud.ClienteJpaRepository;
import co.com.cesde.arkham.persistence.entity.Cliente;

import java.util.List;
import java.util.Optional;

public class ClienteRepository {
    private ClienteJpaRepository clienteJpaRepository;

    void create(Cliente cliente){
        clienteJpaRepository.save(cliente);
    }

    void update(Cliente cliente){
        clienteJpaRepository.save(cliente);
    }

    void delete(Long id){
        clienteJpaRepository.deleteById(id);
    }

    Optional<List<Cliente>> getByNombre(String nombre){
        return clienteJpaRepository.findByNombreCliente(nombre);
    }
}
