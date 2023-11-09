package co.com.cesde.arkham.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Property {
    private Integer propertyId;
    private Location location;
    private Double price;
    private Boolean free;
    private Owner owner;
    private Offer offer;
    private PropertyType propertyType;
    private Integer room;
    private Integer bathroom;
    private Boolean courtyard;
    private Integer level;
    private Double area;
    private Boolean naturalGas;
    private Boolean laundryArea;
    private Boolean active;

}
