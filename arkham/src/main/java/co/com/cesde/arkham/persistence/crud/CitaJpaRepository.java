package co.com.cesde.arkham.persistence.crud;

import co.com.cesde.arkham.persistence.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitaJpaRepository extends JpaRepository<Cita,Integer> {

    Optional<List<Cita>> findByFechaCita(LocalDate CitaFecha);

    Optional<Cita> findByIdCita(Integer appointmentId);
}
