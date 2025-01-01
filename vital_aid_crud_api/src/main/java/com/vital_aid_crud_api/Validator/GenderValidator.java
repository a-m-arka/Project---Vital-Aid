package com.vital_aid_crud_api.Validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.vital_aid_crud_api.Validation.ValidGender;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {

    private static final Set<String> ALLOWED_DAYS = new HashSet<>(
            Arrays.asList("Male", "Female", "Other"));

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        if(gender == null || gender.trim().isEmpty()){
            return false;
        }
        if(!ALLOWED_DAYS.contains(gender.trim())){
            return false;
        }

        for(String g: ALLOWED_DAYS){
            if(g.equalsIgnoreCase(gender.trim())){
                return true;
            }
         }

        return false;

    }

}
