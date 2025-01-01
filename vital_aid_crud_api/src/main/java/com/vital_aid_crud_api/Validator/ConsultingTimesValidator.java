package com.vital_aid_crud_api.Validator;

import com.vital_aid_crud_api.Entity.ConsultingTimes;
import com.vital_aid_crud_api.Validation.ValidConsultTimes;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConsultingTimesValidator implements ConstraintValidator<ValidConsultTimes, ConsultingTimes> {

    @Override
    public boolean isValid(ConsultingTimes consultingTimes, ConstraintValidatorContext context) {
        if (consultingTimes.getStartTime() == null || consultingTimes.getEndTime() == null) {
            return true; // Let @NotBlank handle null/empty cases
        }

        try {
            // Parse the times
            String[] start = consultingTimes.getStartTime().toString().split(":");
            String[] end = consultingTimes.getEndTime().toString().split(":");

            int startHour = Integer.parseInt(start[0]);
            int startMinute = Integer.parseInt(start[1]);
            int endHour = Integer.parseInt(end[0]);
            int endMinute = Integer.parseInt(end[1]);

            // Check if end time is after start time
            return (endHour > startHour) || (endHour == startHour && endMinute > startMinute);

        } catch (NumberFormatException e) {
            return false; // Invalid format will be caught by @Pattern
        }
    }
}

