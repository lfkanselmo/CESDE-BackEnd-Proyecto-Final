package co.com.cesde.arkham.domain.dto.user;

import co.com.cesde.arkham.domain.User;

public record UserListRecord(String firstName, String lastName, String phone) {

    public UserListRecord(User user){
        this(user.getFirstName(),user.getLastName(), user.getPhone());
    }
}
