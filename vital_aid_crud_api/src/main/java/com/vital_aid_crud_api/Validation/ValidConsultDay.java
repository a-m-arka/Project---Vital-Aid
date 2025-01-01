package com.vital_aid_crud_api.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vital_aid_crud_api.Validator.ConsultingDaysValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConsultingDaysValidator.class)
public @interface ValidConsultDay {

    String message() default "Invalid day";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
