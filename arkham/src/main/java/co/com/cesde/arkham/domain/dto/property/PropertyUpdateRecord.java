package co.com.cesde.arkham.domain.dto.property;

import co.com.cesde.arkham.domain.Property;
import jakarta.validation.constraints.NotNull;

public record PropertyUpdateRecord(
        @NotNull
        Long propertyId,
        Double price,
        Boolean free,
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
        String city
) {
    public PropertyUpdateRecord(Property property){
        this(
                property.getPropertyId(),
                property.getPrice(),
                property.getFree(),
                property.getOwnerId(),
                property.getOffer(),
                property.getPropertyType(),
                property.getRooms(),
                property.getBathrooms(),
                property.getCourtyard(),
                property.getLevel(),
                property.getArea(),
                property.getNaturalGas(),
                property.getLaundryArea(),
                property.getAddress(),
                property.getDistrict(),
                property.getCity()
        );
    }
}
