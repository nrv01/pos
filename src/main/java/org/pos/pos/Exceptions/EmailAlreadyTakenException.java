package org.pos.pos.Exceptions;

public class EmailAlreadyTakenException extends RuntimeException{
    public EmailAlreadyTakenException(String email){
        super("La dirección " + email + "ya ha sido tomada.");
    }
}
