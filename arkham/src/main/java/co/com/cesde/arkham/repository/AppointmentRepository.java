package co.com.cesde.arkham.repository;

import co.com.cesde.arkham.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment> getByDate(LocalDate date);

    List<Appointment> getByPropertyId(Long propertyId);

    @Query(
            """
            select a from Appointment a
            where a.date >= :today and a.clientId = :clientId
            """
    )
    List<Appointment> getAppointmentByClientIdAndDate(Long clientId, LocalDate today);

    @Query(
            """
            select a from Appointment a
            where a.date =:date and a.startTime =:time
            """
    )
    Appointment getAppointmentByDateAndStartTime(LocalDate date, LocalTime time);

    Appointment findAppointmentByDateAndStartTimeAndUserId(LocalDate date, LocalTime localTime, Long userId);

    @Query(
            """
            select a from Appointment a
            inner join User u on a.user.username = u.username
            where u.username =:username and a.date =:date
            """
    )
    List<Appointment> findAppointmentByDateAndUsername(LocalDate date, String username);

    @Query(
            """
            select a from Appointment a
            where a.date =:date and a.startTime =:startTime and a.clientId =:clientId
            """
    )
    Appointment getByDateAndStartTimeAndClientId(LocalDate date, LocalTime startTime, Long clientId);

    @Query(
            """
            select a from Appointment a
            where a.date =:date and a.startTime =:startTime and a.userId =:userId
            """
    )
    Appointment getAppointmentByDateAndStartTimeAndUserId(LocalDate date, LocalTime startTime, Long userId);

    @Query(
            """
            select a from Appointment a
            where a.date =:date and a.startTime =:startTime and a.propertyId =:propertyId
            """
    )
    Appointment getByDateAndStartTimeAndPropertyId(LocalDate date, LocalTime startTime, Long propertyId);
}
