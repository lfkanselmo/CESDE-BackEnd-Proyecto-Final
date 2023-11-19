package co.com.cesde.arkham.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserUpdateRecord(
        @NotNull
        Long userId,
        @Email
        String userEmail,
        String password,
        String rol,
        String firstName,
        String lastName,
        @Pattern(regexp = "\\d{7,11}")
        String phone
) {
}
