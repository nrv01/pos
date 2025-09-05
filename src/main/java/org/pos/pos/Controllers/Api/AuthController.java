package org.pos.pos.Controllers.Api;

import org.pos.pos.Dto.Auth.LoginRequest;
import org.pos.pos.Dto.Auth.LoginResponse;
import org.pos.pos.Services.Interfaces.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest authRequest) {
        return authService.login(authRequest.email(), authRequest.password());
    }
}
