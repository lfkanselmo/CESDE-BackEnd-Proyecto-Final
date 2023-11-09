package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropietarioJpaRepository extends JpaRepository<Propietario,Integer> {

    List<Propietario> findByNombrePropietario(String nombrePropietario);
}
