package co.com.cesde.arkham.domain.dto.appointment;

import co.com.cesde.arkham.domain.Appointment;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentUpdateRecord(
        @NotNull
        Integer appointmentId,

        Integer clientId,
        LocalTime startTime,
        LocalTime endTime,
        LocalDate appointmentDate) {
    public AppointmentUpdateRecord(Appointment appointment) {
        this(appointment.getAppointmentId(), appointment.getAppointmentId(), appointment.getStartTime(), appointment.getEndTime(), appointment.getAppointmentDate());
    }
}
