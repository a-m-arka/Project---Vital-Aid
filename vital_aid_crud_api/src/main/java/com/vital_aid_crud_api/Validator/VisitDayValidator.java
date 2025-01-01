package com.vital_aid_crud_api.Validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.vital_aid_crud_api.Validation.ValidVisitDay;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VisitDayValidator implements ConstraintValidator<ValidVisitDay, String> {

    private static final Set<String> ALLOWED_DAYS = new HashSet<>(
        Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"));

        @Override
        public boolean isValid(String day, ConstraintValidatorContext context) {
            if (day == null || day.trim().isEmpty()) {
                return false; // Reject null or empty values
            }
        
            // Normalize input (case-insensitive check)
            return ALLOWED_DAYS.contains(day.trim().toLowerCase());
        }

}
