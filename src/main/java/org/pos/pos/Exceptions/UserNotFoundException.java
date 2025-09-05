package org.pos.pos.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("Usuario con ID: " + id.toString() + " no encontrado");
    }
}
