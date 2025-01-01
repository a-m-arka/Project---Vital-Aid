package com.vital_aid_crud_api.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vital_aid_crud_api.Validator.ConsultingTimesValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE, ElementType.FIELD}) 
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConsultingTimesValidator.class)
public @interface ValidConsultTimes {

    String message() default "End time must be after start time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
