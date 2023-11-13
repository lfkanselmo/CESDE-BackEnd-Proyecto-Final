package co.com.cesde.arkham.domain.dto.appointment;

import co.com.cesde.arkham.domain.Appointment;
import co.com.cesde.arkham.domain.dto.client.ClientListRecord;
import co.com.cesde.arkham.domain.dto.property.PropertyListRecord;
import co.com.cesde.arkham.domain.dto.user.UserListRecord;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentListRecord(Integer appointmentId, LocalTime startTime, LocalTime endTime, LocalDate appointmentDate, PropertyListRecord propertyListRecord, UserListRecord userListRecord, ClientListRecord clientListRecord) {

    public AppointmentListRecord(Appointment appointment){
        this(appointment.getAppointmentId(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getAppointmentDate(),
                new PropertyListRecord(appointment.getProperty()),
                new UserListRecord(appointment.getUser()),
                new ClientListRecord(appointment.getClient()));
    }
}
