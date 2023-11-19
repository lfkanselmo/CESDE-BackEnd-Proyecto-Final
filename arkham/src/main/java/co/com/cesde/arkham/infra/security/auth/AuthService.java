package co.com.cesde.arkham.infra.security.auth;

import co.com.cesde.arkham.domain.User;
import co.com.cesde.arkham.domain.service.UserService;
import co.com.cesde.arkham.infra.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserService userService;
    private final JwtService jwtService;
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .userEmail(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .phone(registerRequest.getPhone())
                .rol(registerRequest.getRol())
                .active(true)
                .build();

        userService.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
