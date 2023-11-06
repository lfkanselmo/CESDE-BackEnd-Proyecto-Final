package co.com.cesde.arkham.persistence.mapper;

import co.com.cesde.arkham.domain.Location;
import co.com.cesde.arkham.persistence.entity.Ubicacion;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {DistrictMapper.class})
public interface LocationMapper {
    @Mappings({
            @Mapping(source = "idUbicacion", target = "locationId"),
            @Mapping(source = "inmueble", target = "property"),
            @Mapping(source = "tipoVia", target = "streetType"),
            @Mapping(source = "numeroVia", target = "streetNumber"),
            @Mapping(source = "numero", target = "number"),
            @Mapping(source = "complemento", target = "complement"),
            @Mapping(source = "barrio", target = "district")
    })
    Location toLocation(Ubicacion ubicacion);

    @InheritInverseConfiguration
    Ubicacion toUbicacion(Location location);
}
