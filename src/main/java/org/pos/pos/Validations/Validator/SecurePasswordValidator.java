package org.pos.pos.Validations.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.pos.pos.Validations.Annotations.SecurePassword;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurePasswordValidator implements ConstraintValidator<SecurePassword, String> {
    private static final  Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$&*]).{5,}$");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(s).matches();
    }
}
