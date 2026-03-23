package com.nguyenhien.jwtsecurity.commons.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidVNPhoneValidator.class) // Chỉ định lớn xử lý logic
@Target({ElementType.FIELD, ElementType.PARAMETER}) // Áp dụng chỉ cho biến và tham số
@Retention(RetentionPolicy.RUNTIME) // Giữ thông tin annotation ở runtime để validator có thể truy cập
public @interface ValidVnPhone {
    // Thông báo lỗi mặc định
    String mesage() default "Invalid Vietnamese phone number format";

    // Thông số bắt buộc của Hibernate Validator
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
