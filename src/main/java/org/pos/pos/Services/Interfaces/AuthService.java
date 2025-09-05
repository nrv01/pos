package org.pos.pos.Services.Interfaces;

import org.pos.pos.Dto.Auth.LoginResponse;

public interface IAuthService {
    LoginResponse login(String email, String password);
}
