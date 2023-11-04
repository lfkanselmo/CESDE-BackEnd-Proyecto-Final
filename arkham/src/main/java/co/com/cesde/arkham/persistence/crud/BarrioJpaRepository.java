package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Barrio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarrioJpaRepository extends JpaRepository<Barrio, Long> {
    List<Barrio> findByNombreBarrio(String nombreBarrio);
}
