package co.com.cesde.arkham.dto.client;

import co.com.cesde.arkham.entity.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClientUpdateRecord(
        @NotNull
        Long clientId,
        String firstName,
        String lastName,
        @Pattern(regexp = "\\d{7,11}")
        String phone,
        @Email
        String email
) {

    public ClientUpdateRecord(Client client) {
        this(client.getClientId(), client.getClientFirstName(), client.getClientLastName(), client.getClientPhone(), client.getClientEmail());
    }
}
