package co.com.cesde.arkham.dto.appointment;

import co.com.cesde.arkham.entity.Appointment;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentUpdateRecord(
        @NotNull
        Long appointmentId,

        Long clientId,
        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        LocalTime startTime,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date) {
    public AppointmentUpdateRecord(Appointment appointment) {
        this(appointment.getAppointmentId(), appointment.getAppointmentId(), appointment.getStartTime(), appointment.getDate());
    }
}
