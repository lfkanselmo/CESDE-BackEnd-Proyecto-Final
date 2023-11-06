package co.com.cesde.arkham.persistence.mapper;

import co.com.cesde.arkham.domain.Owner;
import co.com.cesde.arkham.persistence.entity.Propietario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OwnerMapper {
    @Mappings({
            @Mapping(source = "idPropietario", target = "ownerId"),
            @Mapping(source = "nombrePropietario", target = "ownerFirstName"),
            @Mapping(source = "apellidoPropietario", target = "ownerLastName"),
            @Mapping(source = "telefonoPropietario", target = "ownerPhone"),
            @Mapping(source = "emailPropietario", target = "ownerEmail"),
            @Mapping(source = "activo", target = "active")
    })
    Owner toOwner(Propietario propietario);

    @InheritInverseConfiguration
    @Mapping(target = "inmuebles", ignore = true)
    Propietario toPropietario(Owner owner);
}
