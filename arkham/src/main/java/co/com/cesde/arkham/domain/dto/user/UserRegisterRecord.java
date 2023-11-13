package co.com.cesde.arkham.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRegisterRecord(
        @NotBlank
        @Email
        String user,
        @NotBlank
        String password,
        @NotBlank
        String rol,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        @Pattern(regexp = "\\d{7,11}")
        String phone) {
}
