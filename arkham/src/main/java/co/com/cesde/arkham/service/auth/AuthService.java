package co.com.cesde.arkham.service.auth;

import co.com.cesde.arkham.dto.auth.AuthResponse;
import co.com.cesde.arkham.dto.auth.LoginRequest;
import co.com.cesde.arkham.dto.auth.RegisterRequest;



public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}
