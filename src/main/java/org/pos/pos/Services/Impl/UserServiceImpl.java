package org.pos.pos.Services.Impl;

import org.pos.pos.Dto.User.UserRegistrationRequest;
import org.pos.pos.Dto.User.UserResponse;
import org.pos.pos.Exceptions.EmailAlreadyTakenException;
import org.pos.pos.Exceptions.UserNotFoundException;
import org.pos.pos.Mappers.UserMapper;
import org.pos.pos.Repositories.UserRepository;
import org.pos.pos.Services.Interfaces.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UserResponse> getAllUsers(String searchTerm, Pageable pageable) {
        return userRepository.searchByFullnameAndEmail(searchTerm, pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public UserResponse createUser(UserRegistrationRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyTakenException(user.getEmail());
                });

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }


    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(user -> userMapper.toResponse(user));
    }

    @Override
    public UserResponse updateUser(Long id, UserRegistrationRequest request) {
        var userFound = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userFound.setEmail(request.getEmail());
        userFound.setName(request.getName());
        userFound.setSurname(request.getSurname());

        if (StringUtils.hasText(request.getPassword())) {
            userFound.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        var updatedUser = userRepository.save(userFound);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        var userExists = userRepository.findById(id);
        if (userExists.isPresent()) {
            var user = userExists.get();
            user.setDeletedAt(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}
