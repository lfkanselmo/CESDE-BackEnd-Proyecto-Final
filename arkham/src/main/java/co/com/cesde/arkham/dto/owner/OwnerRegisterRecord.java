package co.com.cesde.arkham.dto.owner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record OwnerRegisterRecord(
        @NotNull
        Long ownerId,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        @Pattern(regexp = "\\d{7,11}")
        String phone,
        @NotBlank
        @Email
        String email) {
}
