package co.com.cesde.arkham.entity;

import co.com.cesde.arkham.dto.appointment.AppointmentRegisterRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cita")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long appointmentId;
    @Column(name = "id_inmueble")
    private Long propertyId;
    @Column(name = "id_usuario")
    private Long userId;
    @Column(name = "id_cliente")
    private Long clientId;
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_inicio")
    private LocalTime startTime;
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_final")
    private LocalTime endTime;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_cita")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "id_inmueble", insertable = false, updatable = false)
    private Property property;
    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Client client;

    public Appointment(AppointmentRegisterRecord appointmentRegisterRecord) {
        this.userId = appointmentRegisterRecord.userId();
        this.clientId = appointmentRegisterRecord.clientId();
        this.propertyId = appointmentRegisterRecord.propertyId();
        this.startTime = appointmentRegisterRecord.startTime();
        this.endTime = appointmentRegisterRecord.startTime().plusHours(1);
        this.date = appointmentRegisterRecord.date();
    }
}
