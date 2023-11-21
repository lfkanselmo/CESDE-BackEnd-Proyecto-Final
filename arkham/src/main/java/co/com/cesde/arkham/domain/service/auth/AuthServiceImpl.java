package co.com.cesde.arkham.domain.service.auth;

import co.com.cesde.arkham.domain.User;
import co.com.cesde.arkham.domain.dto.auth.AuthResponse;
import co.com.cesde.arkham.domain.dto.auth.LoginRequest;
import co.com.cesde.arkham.domain.dto.auth.RegisterRequest;
import co.com.cesde.arkham.domain.repository.UserRepository;
import co.com.cesde.arkham.infra.security.config.JwtService;
import co.com.cesde.arkham.persistence.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .userEmail(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .role(request.getRole())
                .active(true)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.getByUsername(request.getEmail()).orElseThrow();
        System.out.println(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }
}
