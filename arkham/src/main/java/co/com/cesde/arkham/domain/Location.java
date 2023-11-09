package co.com.cesde.arkham.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {
    private Integer locationId;
    private Property property;
    private String address;
    private String district;
    private String city;

}
