package org.pos.pos;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.pos.pos.Config.Security.Jwt.JwtUtil;
import org.pos.pos.Controllers.Api.UserController;
import org.pos.pos.Dto.User.UserRegistrationRequest;
import org.pos.pos.Dto.User.UserResponse;
import org.pos.pos.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(JwtUtil.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private UserService userService;


    @Test
    void shouldReturnPageOfUsers() throws Exception {
        List<UserResponse> users = new ArrayList<>();
        users.add(new UserResponse(1L, "user1@example.com", "User 1", "U1", "+51990000001", LocalDateTime.now(), LocalDateTime.now()));
        users.add(new UserResponse(2L, "user2@example.com", "User 2", "U2", "+51990000002", LocalDateTime.now(), LocalDateTime.now()));

        Page<UserResponse> pagedResponse = new PageImpl(users);
        Mockito.when(userService.getAllUsers(Mockito.eq(""), Mockito.any(Pageable.class)))
                .thenReturn(pagedResponse);

        mockMvc.perform(get("/api/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].email").value("user1@example.com"))
                .andExpect(jsonPath("$.content[1].email").value("user2@example.com"));
    }



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

    @Test
    void shouldReturnNotFoundWhenNotExists() throws Exception {
        Mockito.when(userService.getUserById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/user/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Usuario no encontrado"));
    }

    @Test
    void shouldReturnCreatedUserSuccessfully() throws Exception {
        Long userId = 1L;
        var request = new UserRegistrationRequest();
        request.setEmail("created@example.com");
        request.setName("Created User");
        request.setSurname("Created");
        request.setPhoneNumber("+51999111222");
        request.setPassword("password");

        var createUser = new UserResponse(
                userId,
                request.getEmail(),
                request.getName(),
                request.getSurname(),
                request.getPhoneNumber(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Mockito.when(userService.createUser(Mockito.any(UserRegistrationRequest.class)))
                .thenReturn(createUser);

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "email": "created@example.com",
                                "name": "Created User",
                                "surname": "Created",
                                "phoneNumber": "+51999111222",
                                "password": "password"
                            }
                            """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Usuario creado exitosamente"))
                .andExpect(jsonPath("$.data.email").value("created@example.com"))
                .andExpect(jsonPath("$.data.name").value("Created User"))
                .andExpect(jsonPath("$.data.surname").value("Created"))
                .andExpect(jsonPath("$.data.phoneNumber").value("+51999111222"));
    }


    @Test
    void shouldReturnUpdatedUserSuccessfully() throws Exception {
        Long userId = 1L;
        var request = new UserRegistrationRequest();
        request.setEmail("updated@example.com");
        request.setName("Updated User");
        request.setSurname("Updated");
        request.setPhoneNumber("+51999111222");

        var updated = new UserResponse(
                userId,
                request.getEmail(),
                request.getName(),
                request.getSurname(),
                request.getPhoneNumber(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Mockito.when(userService.updateUser(Mockito.anyLong(), Mockito.any(UserRegistrationRequest.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "email": "updated@example.com",
                            "name": "Updated User",
                            "surname": "Updated",
                            "phoneNumber": "+51999111222"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Usuario actualizado exitosamente"))
                .andExpect(jsonPath("$.data.email").value("updated@example.com"))
                .andExpect(jsonPath("$.data.name").value("Updated User"))
                .andExpect(jsonPath("$.data.surname").value("Updated"))
                .andExpect(jsonPath("$.data.phoneNumber").value("+51999111222"));
    }


}
