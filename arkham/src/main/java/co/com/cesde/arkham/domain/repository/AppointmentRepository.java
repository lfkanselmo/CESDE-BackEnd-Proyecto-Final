package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);

    Optional<Appointment> getByAppointmentId(Long appointmentId);

    void delete(Long appointmentId);

    List<Appointment> getByAppointmentDate(LocalDate appointmentDate);

    List<Appointment> getByPropertyId(Long propertyId);

    Page<Appointment> getAll(Pageable pagination);

    Boolean existsById(Long appointmentId);
}
