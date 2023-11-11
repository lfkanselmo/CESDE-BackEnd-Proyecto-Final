package co.com.cesde.arkham.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {
    private Integer appointmentId;
    private Integer propertyId;
    private Integer userId;
    private Integer clientId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate appointmentDate;
    private Property property;
    private User user;
    private Client client;
}
