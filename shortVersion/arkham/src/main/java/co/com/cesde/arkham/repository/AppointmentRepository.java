package co.com.cesde.arkham.repository;

import co.com.cesde.arkham.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment> getByDate(LocalDate date);

    List<Appointment> getByPropertyId(Long propertyId);
}
