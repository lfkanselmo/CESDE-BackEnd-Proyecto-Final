package co.com.cesde.arkham.service.auth;

import co.com.cesde.arkham.config.JwtService;
import co.com.cesde.arkham.dto.auth.AuthResponse;
import co.com.cesde.arkham.dto.auth.LoginRequest;
import co.com.cesde.arkham.dto.auth.RegisterRequest;
import co.com.cesde.arkham.entity.Role;
import co.com.cesde.arkham.entity.Token;
import co.com.cesde.arkham.entity.TokenType;
import co.com.cesde.arkham.infra.exception.NotFoundValidation;
import co.com.cesde.arkham.repository.TokenRepository;
import co.com.cesde.arkham.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import co.com.cesde.arkham.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword())
        );

        var user = userRepository.findByUsername(request.getEmail())
                .orElseThrow(() -> new ValidationException("No existe usuario registrado con el email " + request.getEmail()));

        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,jwtToken);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getEmail());
        if(userOptional.isEmpty()) {
            var user = User.builder()
                    .username(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .userFirstName(request.getFirstName())
                    .userLastName(request.getLastName())
                    .userPhone(request.getPhone())
                    .role(Role.valueOf(request.getRole()))
                    .active(true)
                    .build();
            var savedUser = userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            saveUserToken(savedUser, jwtToken);
            return AuthResponse.builder()
                    .token(jwtToken).build();
        }else {
            User user = userOptional.get();
            if (user.getActive() == false){
                user.setActive(true);
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setUserFirstName(request.getFirstName());
                user.setUserLastName(request.getLastName());
                user.setUserPhone(request.getPhone());
                user.setRole(Role.valueOf(request.getRole()));

                var savedUser = userRepository.save(user);
                var jwtToken = jwtService.generateToken(user);
                saveUserToken(savedUser, jwtToken);
                return AuthResponse.builder()
                        .token(jwtToken).build();
            }
        }

        throw new ValidationException("Este email ya se encuetra registrado");
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getUserId());
        if(validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
