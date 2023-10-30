package co.com.inmobiliariaapp.entidades;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private Long idCita;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private LocalDate fechaCita;
    private Inmueble inmueble;
    private Administrador administrador;
    private Cliente cliente;

    public Cita(Long idCita, LocalTime horaInicio, LocalTime horaFinal, LocalDate fechaCita, Inmueble inmueble, Administrador administrador, Cliente cliente) {
        this.idCita = idCita;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.fechaCita = fechaCita;
        this.inmueble = inmueble;
        this.administrador = administrador;
        this.cliente = cliente;
    }

    public Cita(LocalTime horaInicio, LocalTime horaFinal, LocalDate fechaCita, Inmueble inmueble, Administrador administrador, Cliente cliente) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.fechaCita = fechaCita;
        this.inmueble = inmueble;
        this.administrador = administrador;
        this.cliente = cliente;
    }

    // Getters & Setters

    public long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
