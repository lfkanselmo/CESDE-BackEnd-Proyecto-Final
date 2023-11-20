package co.com.cesde.arkham.domain.service.auth;

import co.com.cesde.arkham.domain.dto.auth.AuthResponse;
import co.com.cesde.arkham.domain.dto.auth.LoginRequest;
import co.com.cesde.arkham.domain.dto.auth.RegisterRequest;

public interface AuthService {
    public AuthResponse register(RegisterRequest request);
    public AuthResponse login(LoginRequest request);
}
