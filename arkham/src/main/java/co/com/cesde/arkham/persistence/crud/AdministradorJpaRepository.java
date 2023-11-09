package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdministradorJpaRepository extends JpaRepository<Administrador, Integer> {
    List<Administrador> findByNombreAdministrador(String nombre);
}
