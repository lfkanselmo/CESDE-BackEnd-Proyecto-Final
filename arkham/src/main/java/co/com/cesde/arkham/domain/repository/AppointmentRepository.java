package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Optional<Appointment> save(Appointment appointment);

    Optional<Appointment> getByAppointmentId(Integer appointmentId);

    void delete(Integer appointmentId);

    Optional<List<Appointment>> getByAppointmentDate(LocalDate appointmentDate);

    Optional<List<Appointment>> getByPropertyId(Integer propertyId);

    Optional<Page<Appointment>> getAll(Pageable pagination);
}
