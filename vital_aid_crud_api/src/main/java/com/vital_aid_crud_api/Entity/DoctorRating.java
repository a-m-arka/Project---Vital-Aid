package com.vital_aid_crud_api.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor_ratings_reviews")
public class DoctorRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorRatingId;

    private Float rating;
    private String review;

    @ManyToOne
    @JoinColumn(name = "ratingMadeByUser")
    private User ratingMadeByUser;

    @ManyToOne
    @JoinColumn(name = "ratingMadeForDoctor")
    private Doctor ratingMadeForDoctor;

    public DoctorRating() {
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

    
    public User getRatingMadeByUser() {
        return ratingMadeByUser;
    }

    public void setRatingMadeByUser(User ratingMadeByUser) {
        this.ratingMadeByUser = ratingMadeByUser;
    }

    public Doctor getRatingMadeForDoctor() {
        return ratingMadeForDoctor;
    }

    public void setRatingMadeForDoctor(Doctor ratingMadeForDoctor) {
        this.ratingMadeForDoctor = ratingMadeForDoctor;
    }

}
