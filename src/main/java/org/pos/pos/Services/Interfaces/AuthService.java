package org.pos.pos.Services.Interfaces;

import org.pos.pos.Dto.Auth.LoginResponse;

public interface AuthService {
    LoginResponse login(String email, String password);
}
