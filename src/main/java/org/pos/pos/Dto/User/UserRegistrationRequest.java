package org.pos.pos.Dto.User;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.pos.pos.Validations.Annotations.SecurePassword;
import org.pos.pos.Validations.Groups.ValidationGroups;
import org.pos.pos.Validations.Groups.ValidationGroups.OnCreateGroup;

@Data
@AllArgsConstructor
@NotBlank
public class UserRegistrationRequest {

    @Email(message = "El e-mail debe tener un formato válido.")
    @NotBlank(message = "El e-mail es requerido.")
    @Size(max = 255, message = "El e-mail no puede tener más de 255 caracteres")
    private String email;


    @NotBlank(message = "La contraseña es requerida.", groups = {OnCreateGroup.class})
    @SecurePassword()
    @Size(max = 255, message = "La contraseña no puede tener más de 255 caracteres")
    private String password;

    @NotBlank(message = "El nombre es requerido.")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
    private String name;

    @NotBlank(message = "El apellido es requerido.")
    @Size(max = 255, message = "El apellido no puede tener más de 255 caracteres")
    private String surname;

    @Size(max = 50, message = "El número de teléfono no puede tener más de 50 caracteres")
    private String phoneNumber;
}
