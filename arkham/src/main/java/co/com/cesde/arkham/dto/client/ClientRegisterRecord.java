package co.com.cesde.arkham.dto.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientRegisterRecord(
        @NotNull
        Long clientId,

        String firstName,

        String lastName,

        @Pattern(regexp = "\\d{7,11}")
        String phone,

        @Email
        String email) {
}
