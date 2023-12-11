package co.com.cesde.arkham.dto.appointment;

import co.com.cesde.arkham.entity.Appointment;
import jakarta.validation.constraints.NotBlank;

public record AppointmentDateUsernameRecord(
        @NotBlank
        String date,
        @NotBlank
        String userEmail
) {
}
