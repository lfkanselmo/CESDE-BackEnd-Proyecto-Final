package co.com.cesde.arkham.dto.appointment;

import co.com.cesde.arkham.dto.client.ClientListRecord;
import co.com.cesde.arkham.dto.property.PropertyListRecord;
import co.com.cesde.arkham.dto.user.UserListRecord;
import co.com.cesde.arkham.entity.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentListRecord(Long appointmentId,
                                    LocalTime startTime,
                                    LocalTime endTime,
                                    LocalDate date,
                                    PropertyListRecord propertyListRecord,
                                    UserListRecord userListRecord,
                                    ClientListRecord clientListRecord) {

    public AppointmentListRecord(Appointment appointment){
        this(appointment.getAppointmentId(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getDate(),
                new PropertyListRecord(appointment.getProperty()),
                new UserListRecord(appointment.getUser()),
                new ClientListRecord(appointment.getClient()));
    }
}
