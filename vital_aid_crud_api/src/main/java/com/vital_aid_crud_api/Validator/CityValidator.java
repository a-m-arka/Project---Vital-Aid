package com.vital_aid_crud_api.Validator;

import java.util.Arrays;
import java.util.List;

import com.vital_aid_crud_api.Validation.ValidCity;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CityValidator implements ConstraintValidator<ValidCity, String> {


    List<String> City = Arrays.asList(
        "Barisal", "Chittagong", "Dhaka", "Khulna", "Mymensingh", "Rajshahi", "Rangpur", "Sylhet"

    );
    @Override
    public boolean isValid(String city, ConstraintValidatorContext context) {
        if (city == null || city.trim().isEmpty()) {
            return false; // Ensure null or empty values are invalid
        }
        for (String c : City) {
            if (c.equalsIgnoreCase(city.trim())) { // Case-insensitive comparison
                return true;
            }
        }
        return false;
    }

}
