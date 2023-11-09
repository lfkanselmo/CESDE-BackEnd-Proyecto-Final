package co.com.cesde.arkham.persistence.mapper;

import co.com.cesde.arkham.domain.Administrator;
import co.com.cesde.arkham.persistence.entity.Administrador;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {
    @Mappings({
            @Mapping(source = "idAdministrador", target = "administratorId"),
            @Mapping(source = "nombreAdministrador", target = "administratorFirstName"),
            @Mapping(source = "apellidoAdministrador", target = "administratorLastName"),
            @Mapping(source = "telefonoAdministrador", target = "administratorPhone"),
            @Mapping(source = "emailAdministrador", target = "administratorEmail"),
            @Mapping(source = "activo", target = "active")
    })
    Administrator toAdministrator(Administrador administrador);
    List<Administrator> toAdministrators(List<Administrador> administradores);
    @InheritInverseConfiguration
    Administrador toAdministrador(Administrator administrator);
}
