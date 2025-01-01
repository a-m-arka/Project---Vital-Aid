package com.vital_aid_crud_api.Validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.vital_aid_crud_api.Validation.ValidConsultDay;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConsultingDaysValidator implements ConstraintValidator<ValidConsultDay, Set<String>>{

    private static final Set<String> ALLOWED_DAYS = new HashSet<>(
            Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Everyday")
    );

    @Override
    public boolean isValid(Set<String> consultationDays, ConstraintValidatorContext context) {
        if (consultationDays == null || consultationDays.isEmpty()) {
            return false; // Field cannot be empty or null
        }
        // Ensure all days in the set are valid
        for (String day : consultationDays) {
            if (!ALLOWED_DAYS.contains(day)) {
                return false;
            }
        }
        return true;
    }

}
