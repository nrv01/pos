package org.pos.pos.Validations.Annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.pos.pos.Validations.Validator.SecurePasswordValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SecurePasswordValidator.class)
public @interface SecurePassword {
    String message() default "La contraseña debe tener al menos una mayúscula, una minúscula, un número, un caracter especial y mínimo 5 caracteres.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
