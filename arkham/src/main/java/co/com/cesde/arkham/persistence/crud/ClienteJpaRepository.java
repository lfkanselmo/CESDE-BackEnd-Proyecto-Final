package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteJpaRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNombreCliente(String nombre);
}
