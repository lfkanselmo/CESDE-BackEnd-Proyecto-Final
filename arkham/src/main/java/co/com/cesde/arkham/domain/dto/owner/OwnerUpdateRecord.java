package co.com.cesde.arkham.domain.dto.owner;

import co.com.cesde.arkham.domain.Owner;
import jakarta.validation.constraints.NotNull;

public record OwnerUpdateRecord(
        @NotNull
        Integer ownerId,
        String ownerFirstName,
        String ownerLastName,
        String ownerPhone,
        String ownerEmail
) {
    public OwnerUpdateRecord(Owner owner){
        this(owner.getOwnerId(),owner.getOwnerFirstName(), owner.getOwnerLastName(), owner.getOwnerPhone(), owner.getOwnerEmail());
    }
}
