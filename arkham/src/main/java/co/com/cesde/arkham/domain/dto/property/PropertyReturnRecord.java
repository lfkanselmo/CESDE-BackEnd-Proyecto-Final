package co.com.cesde.arkham.domain.dto.property;

import co.com.cesde.arkham.domain.Property;

public record PropertyReturnRecord(Long id ,
                                   Double price,
                                   Boolean availability,
                                   String offer,
                                   String properyType,
                                   Integer rooms,
                                   Integer bathrooms,
                                   Boolean courtyard,
                                   Integer level,
                                   Double area,
                                   Boolean naturalGas,
                                   Boolean laundryArea,
                                   String address,
                                   String district,
                                   String city) {
    public PropertyReturnRecord(Property property) {
        this(property.getPropertyId(),
                property.getPrice(),
                property.getAvailability(),
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
                property.getCity());
    }
}
