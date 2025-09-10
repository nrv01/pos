package org.pos.pos.Controllers.Api;

import org.pos.pos.Dto.Common.BaseResponse;
import org.pos.pos.Dto.User.UserRegistrationRequest;
import org.pos.pos.Dto.User.UserResponse;
import org.pos.pos.Services.Interfaces.UserService;
import org.pos.pos.Validations.Groups.ValidationGroups.OnCreateGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> index(Pageable pageable, @RequestParam(value = "searchTerm", required = false, defaultValue = "") String searchTerm) {
        var data = userService.getAllUsers(searchTerm, pageable);
        return ResponseEntity.ok().body(data);
    }

    @PostMapping
    public ResponseEntity<UserResponse> store(@RequestBody @Validated(OnCreateGroup.class) UserRegistrationRequest request) {
        var userCreated = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseResponse(true, "Eliminado correctamente")
        );
    }
}
