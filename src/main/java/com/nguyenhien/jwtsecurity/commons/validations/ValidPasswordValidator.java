package com.nguyenhien.jwtsecurity.commons.validations;

import com.nguyenhien.jwtsecurity.commons.constants.ValidationConstants;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Consider empty value as valid, use @NotBlank for non-empty validation
        }
        // Regular expression to match password requirements
        return value.matches(ValidationConstants.STRONG_PASS_REGEX);
    }

}
