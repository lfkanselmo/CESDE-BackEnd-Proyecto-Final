package co.com.cesde.arkham.persistence.mapper;

import co.com.cesde.arkham.domain.District;
import co.com.cesde.arkham.persistence.entity.Barrio;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistrictMapper {
    @Mappings({
            @Mapping(source = "idBarrio", target = "districtId"),
            @Mapping(source = "nombreBarrio", target = "districtName"),
            @Mapping(source = "activo", target = "active")
    })
    District toDistrict(Barrio barrio);

    List<District> toDistricts(List<Barrio> barrios);

    @InheritInverseConfiguration
    @Mapping(target = "ubicaciones", ignore = true)
    Barrio toBarrio(District district);
}
