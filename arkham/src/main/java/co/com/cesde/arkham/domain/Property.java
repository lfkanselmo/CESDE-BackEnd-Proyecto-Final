package co.com.cesde.arkham.domain;

import co.com.cesde.arkham.domain.dto.property.PropertyRegisterRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Property {
    private Long propertyId;
    private Double price;
    private Boolean availability;
    private Long ownerId;
    private String offer;
    private String propertyType;
    private Integer rooms;
    private Integer bathrooms;
    private Boolean courtyard;
    private Integer level;
    private Double area;
    private Boolean naturalGas;
    private Boolean laundryArea;
    private String address;
    private String district;
    private String city;
    private Boolean active;
    private Owner owner;

    public Property(PropertyRegisterRecord propertyRegisterRecord) {
        this.price = propertyRegisterRecord.price();
        this.availability = propertyRegisterRecord.availability();
        this.ownerId = propertyRegisterRecord.ownerId();
        this.offer = propertyRegisterRecord.offer();
        this.propertyType = propertyRegisterRecord.propertyType();
        this.rooms = propertyRegisterRecord.rooms();
        this.bathrooms = propertyRegisterRecord.bathrooms();
        this.courtyard = propertyRegisterRecord.courtyard();
        this.level = propertyRegisterRecord.level();
        this.area = propertyRegisterRecord.area();
        this.naturalGas = propertyRegisterRecord.naturalGas();
        this.laundryArea = propertyRegisterRecord.laundryArea();
        this.address = propertyRegisterRecord.address();
        this.district = propertyRegisterRecord.district();
        this.city = propertyRegisterRecord.city();
        this.active = true;
    }
}
