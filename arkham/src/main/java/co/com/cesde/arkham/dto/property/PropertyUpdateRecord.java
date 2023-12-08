package co.com.cesde.arkham.dto.property;

import co.com.cesde.arkham.entity.Property;
import jakarta.validation.constraints.NotNull;

public record PropertyUpdateRecord(
        @NotNull
        Long propertyId,
        Double price,
        Boolean availability,
        Long ownerId,
        String offer,
        String propertyType,
        Integer rooms,
        Integer bathrooms,
        Boolean courtyard,
        Integer level,
        Double area,
        Boolean naturalGas,
        Boolean laundryArea,
        String address,
        String district,
        String city,
        String image
) {

}
