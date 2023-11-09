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
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate appointmentDate;
    private Property property;
    private Administrator administrator;
    private Client client;
}
