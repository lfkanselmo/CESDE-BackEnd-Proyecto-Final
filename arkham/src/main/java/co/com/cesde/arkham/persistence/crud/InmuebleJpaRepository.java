package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InmuebleJpaRepository extends JpaRepository<Inmueble,Long> {
    Optional<List<Inmueble>> findByNombreBarrio(String nombreBarrio);

    Optional<List<Inmueble>> findByIdPropietario(Long id);

    Optional<List<Inmueble>> findByDisponibilidad(boolean b);
}
