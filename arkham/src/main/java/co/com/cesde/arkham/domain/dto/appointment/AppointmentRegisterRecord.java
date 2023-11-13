package co.com.cesde.arkham.domain.dto.appointment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRegisterRecord(
        @NotNull
        @Email
        Integer userId,
        @NotNull
        Integer clientId,
        @NotBlank
        @DateTimeFormat(pattern = "hh:mm:ss")
        LocalTime startTime,
        @NotBlank
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate appointmentDate) {
}
