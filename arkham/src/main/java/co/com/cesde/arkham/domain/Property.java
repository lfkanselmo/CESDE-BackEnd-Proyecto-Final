package co.com.cesde.arkham.domain;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Property {
    private Integer propertyId;
    private Double price;
    private Boolean free;
    private Integer ownerId;
    private String offer;
    private String propertyType;
    private Integer room;
    private Integer bathroom;
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
}
