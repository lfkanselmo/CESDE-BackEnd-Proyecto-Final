package co.com.cesde.arkham.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Owner {
    private Integer ownerId;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerPhone;
    private String ownerEmail;
    private Boolean active;
}
