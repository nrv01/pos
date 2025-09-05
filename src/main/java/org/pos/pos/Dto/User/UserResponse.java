package org.pos.pos.Dto.User;

public record UserResponse (
        long id,
        String email,
        String name,
        String surname,
        String phoneNumber,
        String createdAt,
        String deletedAt
) {
}
