package co.com.cesde.arkham.dto.user;

import co.com.cesde.arkham.entity.User;

public record UserListRecord(
        Long userId,
        String email,
        String firstName,
        String lastName,
        String phone) {

    public UserListRecord(User user){
        this(user.getUserId(), user.getUsername(), user.getUserFirstName(),user.getUserLastName(), user.getUserPhone());
    }
}
