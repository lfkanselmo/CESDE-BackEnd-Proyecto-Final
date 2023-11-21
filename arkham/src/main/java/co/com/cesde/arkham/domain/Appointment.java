package co.com.cesde.arkham.domain;

import co.com.cesde.arkham.domain.dto.appointment.AppointmentRegisterRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {
    private Long appointmentId;
    private Long propertyId;
    private Long userId;
    private Long clientId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate appointmentDate;
    private Property property;
    private User user;
    private Client client;

    public Appointment(AppointmentRegisterRecord appointmentRegisterRecord) {
        this.userId = appointmentRegisterRecord.userId();
        this.clientId = appointmentRegisterRecord.clientId();
        this.propertyId = appointmentRegisterRecord.propertyId();
        this.startTime = appointmentRegisterRecord.startTime();
        this.endTime = appointmentRegisterRecord.startTime().plusHours(1);
        this.appointmentDate = appointmentRegisterRecord.date();
    }
}
