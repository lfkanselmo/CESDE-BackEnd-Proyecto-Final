package co.com.cesde.arkham.domain.dto.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientRegisterRecord(
        @NotNull
        Integer clientId,
        @NotBlank
        String clientFirstName,
        @NotBlank
        String clientLastName,
        @NotBlank
        @Pattern(regexp = "\\d{7,11}")
        String clientPhone,
        @NotBlank
        @Email
        String clientEmail) {
}
