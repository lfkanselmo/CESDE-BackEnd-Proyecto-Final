package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Appointment;
import co.com.cesde.arkham.domain.repository.AppointmentRepository;
import co.com.cesde.arkham.persistence.crud.CitaJpaRepository;
import co.com.cesde.arkham.persistence.entity.Cita;
import co.com.cesde.arkham.persistence.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class CitaRepository implements AppointmentRepository {
    @Autowired
    private CitaJpaRepository citaJpaRepository;
    @Autowired
    private AppointmentMapper mapper;


    @Override
    public Appointment save(Appointment appointment) {
        Cita cita = mapper.toCita(appointment);
        return mapper.toAppointment(citaJpaRepository.save(cita));
    }

    @Override
    public Optional<Appointment> getById(Integer appointmentId) {
        Optional<Cita> cita = citaJpaRepository.findById(appointmentId);
        return cita.map(citaOpcional -> mapper.toAppointment(citaOpcional));
    }

    @Override
    public void delete(Integer appointmentId) {
        citaJpaRepository.deleteById(appointmentId);
    }

    @Override
    public Optional<List<Appointment>> getByAppointmentDate(LocalDate appointmentDate) {
        List<Cita> citas = citaJpaRepository.findByFechaCita(appointmentDate);
        if(!citas.isEmpty()){
            return Optional.of(citas
                    .stream()
                    .map(cita -> mapper.toAppointment(cita))
                    .toList()) ;
        }
        return Optional.empty();
    }
}
