package co.com.cesde.arkham.dto.owner;

import co.com.cesde.arkham.entity.Owner;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record OwnerUpdateRecord(
        @NotNull
        Long ownerId,
        String firstName,
        String lastName,
        @Pattern(regexp = "\\d{7,11}")
        String phone,
        @Email
        String email
) {
    public OwnerUpdateRecord(Owner owner){
        this(owner.getOwnerId(),owner.getOwnerFirstName(), owner.getOwnerLastName(), owner.getOwnerPhone(), owner.getOwnerEmail());
    }
}
