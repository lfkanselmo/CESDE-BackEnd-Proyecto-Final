package co.com.cesde.arkham.persistence.mapper;

import co.com.cesde.arkham.domain.Property;
import co.com.cesde.arkham.persistence.entity.Inmueble;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "Spring", uses = {OwnerMapper.class})
public interface PropertyMapper {
    @Mappings({
            @Mapping(source = "idInmueble", target = "propertyId"),
            @Mapping(source = "precio", target = "price"),
            @Mapping(source = "disponibilidad", target = "free"),
            @Mapping(source = "idPropietario", target = "ownerId"),
            @Mapping(source = "propietario", target = "owner"),
            @Mapping(source = "oferta", target = "offer"),
            @Mapping(source = "tipoInmueble", target = "propertyType"),
            @Mapping(source = "habitaciones", target = "room"),
            @Mapping(source = "banhos", target = "bathroom"),
            @Mapping(source = "patio", target = "courtyard"),
            @Mapping(source = "nivel", target = "level"),
            @Mapping(source = "area", target = "area"),
            @Mapping(source = "gasNatural", target = "naturalGas"),
            @Mapping(source = "zonaRopa", target = "laundryArea"),
            @Mapping(source = "direccion", target = "address"),
            @Mapping(source = "barrio", target = "district"),
            @Mapping(source = "ciudad", target = "city"),
            @Mapping(source = "activo", target = "active")
    })
    Property toProperty(Inmueble inmueble);
    List<Property> toProperties(List<Inmueble> inmuebles);

    @InheritInverseConfiguration
    Inmueble toInmueble(Property property);

}
