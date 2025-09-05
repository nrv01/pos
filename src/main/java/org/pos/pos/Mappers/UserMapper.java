package org.pos.pos.Mappers;

import org.mapstruct.Mapper;
import org.pos.pos.Dto.User.UserResponse;
import org.pos.pos.Models.User;

@Mapper
public interface UserMapper {
    User mapUserResponseToUser(UserResponse userResponse);
    UserResponse mapUserToUserResponse(User user);
}
