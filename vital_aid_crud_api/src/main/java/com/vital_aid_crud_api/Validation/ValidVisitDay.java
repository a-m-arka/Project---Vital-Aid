package com.vital_aid_crud_api.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vital_aid_crud_api.Validator.VisitDayValidator;

import jakarta.validation.Constraint;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VisitDayValidator.class)
public @interface ValidVisitDay {
    String message() default "Invalid visit day";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
