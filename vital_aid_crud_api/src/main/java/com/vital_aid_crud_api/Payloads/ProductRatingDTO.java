package com.vital_aid_crud_api.Payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class ProductRatingDTO {

    private Long productRatingId;

    @Max(value = 5, message = "Rating must be between 0 and 5")
    @Min(value = 0, message = "Rating must be between 0 and 5")
    private Float rating;

    @Size(max = 1000, message = "Review must not exceed 1000 characters")
    private String review;


    private String productRatedByUserName;
    private String pRatingForProductName;

    public ProductRatingDTO() {
    }

    public Long getProductRatingId() {
        return productRatingId;
    }

    public void setProductRatingId(Long productRatingId) {
        this.productRatingId = productRatingId;
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

    public String getProductRatedByUserName() {
        return productRatedByUserName;
    }

    public void setProductRatedByUserName(String productRatedByUserName) {
        this.productRatedByUserName = productRatedByUserName;
    }

    public String getpRatingForProductName() {
        return pRatingForProductName;
    }

    public void setpRatingForProductName(String pRatingForProductName) {
        this.pRatingForProductName = pRatingForProductName;
    }
}
