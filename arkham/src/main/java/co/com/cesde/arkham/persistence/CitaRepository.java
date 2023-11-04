package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.persistence.crud.CitaJpaRepository;
import co.com.cesde.arkham.persistence.entity.Cita;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CitaRepository {
    private CitaJpaRepository citaJpaRepository;

    void create(Cita cita){
        citaJpaRepository.save(cita);
    }

    void update(Cita cita){
        citaJpaRepository.save(cita);
    }

    Optional<Cita> getById(Long id){
        return citaJpaRepository.findById(id);
    }

    void delete(Long id){
        citaJpaRepository.deleteById(id);
    }

    Optional<List<Cita>> getByInmuebleAndFecha(Long idInmueble){
        return citaJpaRepository.findByIdInmueble(idInmueble);
    }

    public Optional<List<Cita>> getByFecha(LocalDate fecha){
        return citaJpaRepository.findByFecha(fecha);
    }

}
