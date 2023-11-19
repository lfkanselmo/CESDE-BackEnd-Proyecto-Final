package co.com.cesde.arkham.persistence.entity;

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
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;
    @Column(name = "id_inmueble")
    private Long idInmueble;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "id_cliente")
    private Long idCliente;
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_inicio")
    private LocalTime horaInicio;
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_final")
    private LocalTime horaFinal;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_cita")
    private LocalDate fechaCita;
    @ManyToOne
    @JoinColumn(name = "id_inmueble", insertable = false, updatable = false)
    private Inmueble inmueble;
    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;
}
