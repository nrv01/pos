package org.pos.pos.Dto.User;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record UserResponse (
        long id,
        String email,
        String name,
        String surname,
        String phoneNumber,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDateTime deletedAt
) {
}
