package co.com.cesde.arkham.domain;

import co.com.cesde.arkham.domain.dto.owner.OwnerRegisterRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Owner {
    private Long ownerId;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerPhone;
    private String ownerEmail;
    private Boolean active;

    public Owner(OwnerRegisterRecord ownerRegisterRecord) {
        this.ownerId = ownerRegisterRecord.ownerId();
        this.ownerFirstName = ownerRegisterRecord.ownerFirstName();
        this.ownerLastName = ownerRegisterRecord.ownerLastName();
        this.ownerPhone = ownerRegisterRecord.ownerPhone();
        this.ownerEmail = ownerRegisterRecord.ownerEmail();
        this.active = true;
    }
}
