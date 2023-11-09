package co.com.cesde.arkham.persistence.mapper;

import co.com.cesde.arkham.domain.Location;
import co.com.cesde.arkham.persistence.entity.Ubicacion;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    @Mappings({
            @Mapping(source = "idUbicacion", target = "locationId"),
            @Mapping(source = "inmueble", target = "property"),
            @Mapping(source = "direccion", target = "address"),
            @Mapping(source = "barrio", target = "district"),
            @Mapping(source = "ciudad", target = "city")
    })
    Location toLocation(Ubicacion ubicacion);

    @InheritInverseConfiguration
    Ubicacion toUbicacion(Location location);
}
