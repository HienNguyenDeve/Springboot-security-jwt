package com.nguyenhien.jwtsecurity.commons.validations;

import com.nguyenhien.jwtsecurity.commons.constants.ValidationConstants;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidVNPhoneValidator implements ConstraintValidator<ValidVnPhone, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Consider empty value as valid, use @NotBlank for non-empty validation
        }
        // Regular expression to match Vietnamese phone numbers
        return value.matches(ValidationConstants.VN_PHONE_REGEX);
    }
}
