package org.pos.pos.Dto.User;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.pos.pos.Validations.Annotations.SecurePassword;

@Data
@AllArgsConstructor
@NotBlank
public class UserRegistrationRequest {

    @Email(message = "El e-mail debe tener un formato válido.")
    @NotBlank(message = "El e-mail es requerido.")
    private String email;


    @NotBlank(message = "La contraseña es requerida.")
    @SecurePassword
    private String password;

    @NotBlank(message = "El nombre es requerido.")
    private String name;

    @NotBlank(message = "El apellido es requerido.")
    private String surname;

    @NotBlank(message = "El número de telefono es requerido.")
    private String phoneNumber;
}
