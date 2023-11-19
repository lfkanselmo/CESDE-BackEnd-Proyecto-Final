package co.com.cesde.arkham.domain.dto.owner;

import co.com.cesde.arkham.domain.Owner;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record OwnerUpdateRecord(
        @NotNull
        Long ownerId,
        String ownerFirstName,
        String ownerLastName,
        @Pattern(regexp = "\\d{7,11}")
        String ownerPhone,
        @Email
        String ownerEmail
) {
    public OwnerUpdateRecord(Owner owner){
        this(owner.getOwnerId(),owner.getOwnerFirstName(), owner.getOwnerLastName(), owner.getOwnerPhone(), owner.getOwnerEmail());
    }
}
