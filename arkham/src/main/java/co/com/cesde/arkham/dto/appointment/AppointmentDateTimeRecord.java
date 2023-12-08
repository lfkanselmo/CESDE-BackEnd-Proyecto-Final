package co.com.cesde.arkham.dto.appointment;

import co.com.cesde.arkham.entity.Appointment;
import jakarta.validation.constraints.NotBlank;

public record AppointmentDateTimeRecord(
        @NotBlank
        String date,
        String startTime
) {
    public AppointmentDateTimeRecord(Appointment appointment){
        this(
                appointment.getDate().toString(),
                appointment.getStartTime().toString()
        );
    }
}
