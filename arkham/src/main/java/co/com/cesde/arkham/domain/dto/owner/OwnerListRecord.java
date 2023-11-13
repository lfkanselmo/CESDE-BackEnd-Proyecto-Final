package co.com.cesde.arkham.domain.dto.owner;

import co.com.cesde.arkham.domain.Owner;

public record OwnerListRecord(String firstName, String lastName, String phone) {
    public OwnerListRecord(Owner owner){
        this(owner.getOwnerFirstName(), owner.getOwnerLastName(), owner.getOwnerPhone());
    }
}
