package co.com.cesde.arkham.dto.client;

import co.com.cesde.arkham.entity.Client;

public record ClientListRecord(Long id,
                               String firstName,
                               String lastName,
                               String phone,
                               String email) {
    public ClientListRecord(Client client) {
        this(client.getClientId(), client.getClientFirstName(),client.getClientLastName(),client.getClientPhone(),client.getClientEmail());
    }
}
