package co.com.cesde.arkham.dto.property;

import co.com.cesde.arkham.dto.owner.OwnerListRecord;
import co.com.cesde.arkham.entity.Property;

public record PropertyListRecord(Long propertyId ,
                                 Double price,
                                 Boolean availability,
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
                                 String image) {
    public PropertyListRecord(Property property) {
        this(property.getPropertyId(),
                property.getPrice(),
                property.getAvailability(),
                property.getOffer().name(),
                property.getPropertyType().name(),
                property.getRooms(),
                property.getBathrooms(),
                property.getCourtyard(),
                property.getLevel(),
                property.getArea(),
                property.getNaturalGas(),
                property.getLaundryArea(),
                property.getAddress(),
                property.getDistrict(),
                property.getCity(),
                property.getImage());
    }
}
