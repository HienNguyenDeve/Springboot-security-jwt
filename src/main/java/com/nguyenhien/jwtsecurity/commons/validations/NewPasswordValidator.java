package com.nguyenhien.jwtsecurity.commons.validations;

import com.nguyenhien.jwtsecurity.dtos.requests.ChangePasswordRequestDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NewPasswordValidator implements ConstraintValidator<NewPassword, ChangePasswordRequestDTO> {

    @Override
    public boolean isValid(ChangePasswordRequestDTO dto, ConstraintValidatorContext context) {
        // Check if old password is correct

        // Check if new password is equals to old password
        if (dto.getOldPassword().equals(dto.getNewPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("New Password must be different from Old Password")
                    .addPropertyNode("newPassword")
                    .addConstraintViolation();
            return false;
        }

        // Check if confirm password matches new password
        if (!dto.getNewPassword().equals(dto.getConfirmNewPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Confirm New Password does not match New Password")
                    .addPropertyNode("confirmNewPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
