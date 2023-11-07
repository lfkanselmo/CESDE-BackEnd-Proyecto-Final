package co.com.cesde.arkham.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {
    private Long locationId;
    private Property property;
    private String streetType;
    private String streetNumber;
    private String number;
    private String complement;
    private District district;
}
