package com.vital_aid_crud_api.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vital_aid_crud_api.Validator.GenderValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface ValidGender {
    String message() default "Invalid gender";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
