package co.com.cesde.arkham.dto.owner;

import co.com.cesde.arkham.entity.Owner;

public record OwnerListRecord(String firstName, String lastName, String phone, String email) {
    public OwnerListRecord(Owner owner){
        this(owner.getOwnerFirstName(), owner.getOwnerLastName(), owner.getOwnerPhone(), owner.getOwnerEmail());
    }
}
