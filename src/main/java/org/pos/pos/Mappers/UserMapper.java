package org.pos.pos.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.pos.pos.Dto.User.UserRegistrationRequest;
import org.pos.pos.Dto.User.UserResponse;
import org.pos.pos.Models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserResponse response);
    UserResponse toResponse(User user);
    User toEntity(UserRegistrationRequest request);
}
