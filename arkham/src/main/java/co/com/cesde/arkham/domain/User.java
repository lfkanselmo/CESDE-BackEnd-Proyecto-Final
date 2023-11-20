package co.com.cesde.arkham.domain;

import co.com.cesde.arkham.domain.dto.user.UserRegisterRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User implements UserDetails{
    private Long userId;
    private String userEmail;
    private String password;
    private String rol;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean active;

    public User(UserRegisterRecord userRegisterRecord) {
        this.userEmail = userRegisterRecord.user();
        this.password = userRegisterRecord.password();
        this.rol = userRegisterRecord.rol();
        this.firstName = userRegisterRecord.firstName();
        this.lastName = userRegisterRecord.lastName();
        this.phone = userRegisterRecord.phone();
        this.active = true;

    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol));
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
