package co.com.cesde.arkham.dto.appointment;

import co.com.cesde.arkham.entity.Appointment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentUpdateRecord(
        @NotNull
        Long appointmentId,
        @NotNull
        Long userId,
        @NotNull
        Long clientId,
        @NotNull
        Long propertyId,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        LocalTime startTime,
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date) {
}
