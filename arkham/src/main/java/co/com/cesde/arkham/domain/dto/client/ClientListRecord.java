package co.com.cesde.arkham.domain.dto.client;

import co.com.cesde.arkham.domain.Client;

public record ClientListRecord(Integer id, String firstName, String lastName, String phone, String email) {
    public ClientListRecord(Client client) {
        this(client.getClientId(), client.getClientFirstName(),client.getClientLastName(),client.getClientPhone(),client.getClientEmail());
    }
}
