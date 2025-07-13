package com.vital_aid_crud_api.Payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class DoctorRatingDTO {

    private Long doctorRatingId;

    @Max(value = 5, message = "Rating must be between 0 and 5")
    @Min(value = 0, message = "Rating must be between 0 and 5")
    private Float rating;

    @Size(max = 1000, message = "Review must not exceed 1000 characters")
    private String review;


    private String ratedByUserName;
    private String ratingForDoctorName;

    public DoctorRatingDTO() {
    }

    public Long getDoctorRatingId() {
        return doctorRatingId;
    }

    public void setDoctorRatingId(Long doctorRatingId) {
        this.doctorRatingId = doctorRatingId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRatedByUserName() {
        return ratedByUserName;
    }

    public void setRatedByUserName(String ratedByUserName) {
        this.ratedByUserName = ratedByUserName;
    }

    public String getRatingForDoctorName() {
        return ratingForDoctorName;
    }

    public void setRatingForDoctorName(String ratingForDoctorName) {
        this.ratingForDoctorName = ratingForDoctorName;
    }


}
