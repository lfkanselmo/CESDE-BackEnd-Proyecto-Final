package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministradorJpaRepository extends JpaRepository<Administrador, Long> {
    List<Administrador> findByNombreAdministrador(String nombre);
}
