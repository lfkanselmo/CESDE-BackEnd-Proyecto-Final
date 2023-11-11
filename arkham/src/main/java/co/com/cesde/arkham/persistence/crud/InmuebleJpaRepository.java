package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Inmueble;
import co.com.cesde.arkham.persistence.entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InmuebleJpaRepository extends JpaRepository<Inmueble,Integer> {

    Optional<List<Inmueble>> findByDisponibilidad(Boolean b);

    Optional<List<Inmueble>> findByBarrio(String barrio);

    Optional<Inmueble> getByIdInmueble(Integer propertyId);

    Optional<List<Inmueble>> getByIdPropietario(Integer ownerId);
}
