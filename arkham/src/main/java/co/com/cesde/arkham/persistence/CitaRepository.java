package co.com.cesde.arkham.persistence;

import co.com.cesde.arkham.domain.Appointment;
import co.com.cesde.arkham.domain.repository.AppointmentRepository;
import co.com.cesde.arkham.persistence.crud.CitaJpaRepository;
import co.com.cesde.arkham.persistence.entity.Cita;
import co.com.cesde.arkham.persistence.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public Optional<Appointment> getByAppointmentId(Long appointmentId) {
        Optional<Cita> cita = citaJpaRepository.findByIdCita(appointmentId);
        return cita.map(citaOpcional -> mapper.toAppointment(citaOpcional));
    }

    @Override
    public void delete(Long appointmentId) {
        citaJpaRepository.deleteById(appointmentId);
    }

    @Override
    public List<Appointment> getByAppointmentDate(LocalDate appointmentDate) {
        List<Cita> citas = citaJpaRepository.findByFechaCita(appointmentDate);
        return mapper.toAppointments(citas);
    }

    @Override
    public List<Appointment> getByPropertyId(Long propertyId) {
        List<Cita> citas = citaJpaRepository.findByIdInmueble(propertyId);
        return mapper.toAppointments(citas);
    }

    @Override
    public Page<Appointment> getAll(Pageable pagination) {
        Page<Cita> citas = citaJpaRepository.findAll(pagination);
        return citas.map(cita -> mapper.toAppointment(cita));
    }

    @Override
    public Boolean existsById(Long appointmentId) {
        return citaJpaRepository.existsById(appointmentId);
    }
}
