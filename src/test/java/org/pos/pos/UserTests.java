package org.pos.pos;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.pos.pos.Config.Security.Jwt.JwtUtil;
import org.pos.pos.Controllers.Api.UserController;
import org.pos.pos.Dto.User.UserResponse;
import org.pos.pos.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(UserController.class)
@Import(JwtUtil.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserTests {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldReturnUserWhenExists() throws Exception {
        var user = new UserResponse(1L,
                "test@example.com",
                "Test User",
                "Test",
                "+51999888888",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Mockito.when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/user/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.email").value("test@example.com"));
    }
}
