package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteJpaRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNombreClienteAndActivoTrue(String nombreCliente);

    Optional<Cliente> getByIdClienteAndActivoTrue(Long idCliente);

    Page<Cliente> findByActivoTrue(Pageable pagination);
}
