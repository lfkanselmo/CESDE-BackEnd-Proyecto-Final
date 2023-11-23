package co.com.cesde.arkham.service;

import co.com.cesde.arkham.controller.models.AuthResponse;
import co.com.cesde.arkham.controller.models.LoginRequest;
import co.com.cesde.arkham.controller.models.RegisterRequest;



public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}
