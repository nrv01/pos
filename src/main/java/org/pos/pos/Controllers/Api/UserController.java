package org.pos.pos.Controllers.Api;

import org.pos.pos.Dto.Common.ApiResponse;
import org.pos.pos.Dto.User.UserRegistrationRequest;
import org.pos.pos.Dto.User.UserResponse;
import org.pos.pos.Services.Interfaces.UserService;
import org.pos.pos.Validations.Groups.ValidationGroups.OnCreateGroup;
import org.pos.pos.Validations.Groups.ValidationGroups.OnUpdateGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> index(Pageable pageable, @RequestParam(value = "searchTerm", required = false, defaultValue = "") String searchTerm) {
        var data = userService.getAllUsers(searchTerm, pageable);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> find(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok().body(
                        ApiResponse.success("Usuario encontrado", user)
                ))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Usuario no encontrado")));

    }


    @PutMapping
    public ResponseEntity<ApiResponse<UserResponse>> update(@RequestBody @Validated(OnUpdateGroup.class) UserRegistrationRequest request) {
        var userCreated = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success("Usuario creado exitosamente", userCreated)
        );
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> store(@PathVariable Long id, @RequestBody @Validated(OnCreateGroup.class) UserRegistrationRequest request) {
        var userUpdated = userService.updateUser(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("Usuario actualizado exitosamente", userUpdated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("Usuario eliminado correctamente", null)
        );
    }
}
