package co.com.cesde.arkham.persistence.mapper;

import co.com.cesde.arkham.domain.Appointment;
import co.com.cesde.arkham.persistence.entity.Cita;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, UserMapper.class, PropertyMapper.class})
public interface AppointmentMapper {
    @Mappings({
            @Mapping(source = "idCita", target = "appointmentId"),
            @Mapping(source = "horaInicio", target = "startTime"),
            @Mapping(source = "horaFinal", target = "endTime"),
            @Mapping(source = "fechaCita", target = "appointmentDate"),
            @Mapping(source = "idInmueble", target = "propertyId"),
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "inmueble", target = "property"),
            @Mapping(source = "usuario", target = "user"),
            @Mapping(source = "cliente", target = "client")
    })
    Appointment toAppointment(Cita cita);

    List<Appointment> toAppointments(List<Cita> citas);

    @InheritInverseConfiguration
    Cita toCita(Appointment appointment);

    List<Appointment> toCitas(List<Cita> citas);
}