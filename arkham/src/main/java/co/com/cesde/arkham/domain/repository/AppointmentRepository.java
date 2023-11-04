package co.com.cesde.arkham.domain.repository;


import co.com.cesde.arkham.domain.Appointment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    void create(Appointment appointment);

    void update(Appointment appointment);

    Optional<Appointment> getById(Long appointmentId);

    void delete(Long appointmentId);

    Optional<List<Appointment>> getByPropertyAndAppointmentDate(Long propertyId);

    Optional<List<Appointment>> getByAppointmentDate(LocalDate appointmentDate);
}
