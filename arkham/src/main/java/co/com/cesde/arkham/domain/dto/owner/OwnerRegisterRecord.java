package co.com.cesde.arkham.domain.dto.owner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record OwnerRegisterRecord(
        @NotNull
        Integer ownerId,
        @NotBlank
        String ownerFirstName,
        @NotBlank
        String ownerLastName,
        @NotBlank
        @Pattern(regexp = "\\d{7,11}")
        String ownerPhone,
        @NotBlank
        @Email
        String ownerEmail) {
}
