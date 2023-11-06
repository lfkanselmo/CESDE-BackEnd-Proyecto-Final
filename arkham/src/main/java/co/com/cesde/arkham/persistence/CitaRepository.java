package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Appointment;
import co.com.cesde.arkham.domain.repository.AppointmentRepository;
import co.com.cesde.arkham.persistence.crud.CitaJpaRepository;
import co.com.cesde.arkham.persistence.entity.Cita;
import co.com.cesde.arkham.persistence.mapper.AppointmentMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CitaRepository implements AppointmentRepository {
    private CitaJpaRepository citaJpaRepository;
    private AppointmentMapper mapper;


    @Override
    public Appointment create(Appointment appointment) {
        Cita cita = mapper.toCita(appointment);
        citaJpaRepository.save(cita);
        return appointment;
    }

    @Override
    public Appointment update(Appointment appointment) {
        Cita cita = mapper.toCita(appointment);
        citaJpaRepository.save(cita);
        return appointment;
    }

    @Override
    public Optional<Appointment> getById(Long appointmentId) {
        Optional<Cita> cita = citaJpaRepository.findById(appointmentId);
        return cita.map(c -> mapper.toAppointment(c));
    }

    @Override
    public void delete(Long appointmentId) {
        citaJpaRepository.deleteById(appointmentId);
    }

    @Override
    public Optional<List<Appointment>> getByAppointmentDate(LocalDate appointmentDate) {
        List<Cita> citas = citaJpaRepository.findByAppointmentDate(appointmentDate);
        if(!citas.isEmpty()){
            return Optional.of(citas
                    .stream()
                    .map(c -> mapper.toAppointment(c))
                    .toList()) ;
        }
        return Optional.empty();
    }
}
