package org.pos.pos.Services.Interfaces;

import org.pos.pos.Dto.User.UserRegistrationRequest;
import org.pos.pos.Dto.User.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Page<UserResponse> getAllUsers(String searchTerm, Pageable pageable);
    UserResponse createUser(UserRegistrationRequest userRegistrationRequest);
    Optional<UserResponse> getUserById(Long id);
    UserResponse updateUser(Long id, UserRegistrationRequest user);
    void deleteUser(Long id);
}
