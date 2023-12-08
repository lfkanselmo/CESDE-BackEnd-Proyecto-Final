package co.com.cesde.arkham.dto.token;

import jakarta.validation.constraints.NotBlank;

public record ConsultTokenRecord(
        @NotBlank
        String token) {
}
